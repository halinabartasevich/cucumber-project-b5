package io.loop.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class PageObjectGenerator2 {


    public static void main(String[] args) throws IOException {

        // ====== CONFIG ======
        String url = "https://www.loopcamp.io";
        String pageName = "LoopCampPage";               // <— set your class name
        String outDir = "src/test/java/io/loop/pages";       // <— output folder
        // ====================

        Driver.getDriver().get(url);

        String className = sanitizeClassName(pageName);
        String pkg = "io.loop.pages";

        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(pkg).append(";\n\n")
                .append("import org.openqa.selenium.WebElement;\n")
                .append("import org.openqa.selenium.support.FindBy;\n")
                .append("import org.openqa.selenium.support.PageFactory;\n")
                .append("import io.loop.utilities.Driver;\n\n")
                .append("public class ").append(className).append(" {\n\n")
                .append("    public ").append(className).append("() {\n")
                .append("        PageFactory.initElements(Driver.getDriver(), this);\n")
                .append("    }\n\n");

        // Collect candidate elements (inputs, textareas, selects, buttons)
        List<WebElement> elements = new ArrayList<>();
        elements.addAll(Driver.getDriver().findElements(By.xpath("//input[@id or @name or @placeholder or @aria-label]")));
        elements.addAll(Driver.getDriver().findElements(By.xpath("//textarea[@id or @name or @placeholder or @aria-label]")));
        elements.addAll(Driver.getDriver().findElements(By.xpath("//select[@id or @name or @aria-label]")));
        elements.addAll(Driver.getDriver().findElements(By.xpath("//button[@id or @name or @aria-label or normalize-space(text())]")));

        // To avoid duplicate field names
        Map<String,Integer> nameCounts = new HashMap<>();

        for (WebElement el : elements) {
            String tag = safeAttr(el.getTagName());
            String id = safeAttr(el.getAttribute("id"));
            String name = safeAttr(el.getAttribute("name"));
            String placeholder = safeAttr(el.getAttribute("placeholder"));
            String aria = safeAttr(el.getAttribute("aria-label"));
            String type = safeAttr(el.getAttribute("type")); // for inputs (text, email, password, submit…)

            // Pick best locator (stable-first)
            String findBy = null;
            if (notEmpty(id)) {
                findBy = "@FindBy(id = \"" + escape(id) + "\")";
            } else if (notEmpty(name)) {
                findBy = "@FindBy(name = \"" + escape(name) + "\")";
            } else if (notEmpty(aria)) {
                findBy = "@FindBy(xpath = \"//" + tag + "[@aria-label='" + escape(aria) + "']\")";
            } else if (notEmpty(placeholder) && (tag.equals("input") || tag.equals("textarea"))) {
                findBy = "@FindBy(xpath = \"//" + tag + "[@placeholder='" + escape(placeholder) + "']\")";
            } else if (tag.equals("button")) {
                String text = safeInnerText(el);
                if (notEmpty(text)) {
                    findBy = "@FindBy(xpath = \"//button[normalize-space(text())='" + escape(text) + "']\")";
                }
            }
            // Fallback (very generic; skip if no usable locator found)
            if (findBy == null) continue;

            // Build a meaningful variable name
            String baseName = buildName(tag, type, id, name, placeholder, aria);
            String fieldName = dedupe(toCamel(baseName), nameCounts);

            sb.append("    ").append(findBy).append("\n")
                    .append("    public WebElement ").append(fieldName).append(";\n\n");
        }

        sb.append("}\n");

        // Write to <PageName>.java
        String outPath = Paths.get(outDir, className + ".java").toString();
        try (FileWriter writer = new FileWriter(outPath)) {
            writer.write(sb.toString());
        }

        Driver.getDriver().quit();
        System.out.println("✅ Generated " + outPath);
    }

    // ---------- helpers ----------

    private static String buildName(String tag, String type, String id, String name, String placeholder, String aria) {
        // Priority for semantics: aria > placeholder > name > id > tag/type
        String base = firstNonEmpty(aria, placeholder, name, id);
        if (base != null) return base + " " + tagSuffix(tag, type);
        // If nothing meaningful, compose from tag & type
        return (notEmpty(type) ? type + " " : "") + tagSuffix(tag, type);
    }

    private static String tagSuffix(String tag, String type) {
        if ("input".equals(tag)) {
            if (notEmpty(type)) return type + "Input";
            return "input";
        }
        if ("textarea".equals(tag)) return "textArea";
        if ("select".equals(tag)) return "select";
        if ("button".equals(tag)) return "button";
        return tag;
    }

    private static String dedupe(String candidate, Map<String,Integer> counts) {
        counts.putIfAbsent(candidate, 0);
        int c = counts.get(candidate);
        if (c == 0) {
            counts.put(candidate, 1);
            return candidate;
        } else {
            counts.put(candidate, c + 1);
            return candidate + c; // e.g., emailInput, emailInput1, emailInput2…
        }
    }

    private static String toCamel(String s) {
        if (s == null || s.isBlank()) return "element";
        // keep alnum spaces, then camelize
        s = s.replaceAll("[^A-Za-z0-9 ]", " ").replaceAll("[_\\-]+", " ").trim();
        String[] parts = s.split("\\s+");
        StringBuilder out = new StringBuilder(parts[0].toLowerCase());
        for (int i = 1; i < parts.length; i++) {
            out.append(parts[i].substring(0,1).toUpperCase())
                    .append(parts[i].substring(1).toLowerCase());
        }
        // cannot start with digit
        if (!out.toString().isEmpty() && Character.isDigit(out.charAt(0))) {
            out.insert(0, "el");
        }
        return out.toString();
    }

    private static String sanitizeClassName(String name) {
        if (name == null || name.isBlank()) return "GeneratedPage";
        String s = name.replaceAll("[^A-Za-z0-9]", " ").trim();
        String[] parts = s.split("\\s+");
        StringBuilder out = new StringBuilder();
        for (String p : parts) {
            out.append(p.substring(0,1).toUpperCase()).append(p.substring(1));
        }
        if (out.length() == 0 || Character.isDigit(out.charAt(0))) {
            out.insert(0, "Page");
        }
        return out.toString();
    }

    private static String escape(String s) { return s.replace("\"", "\\\""); }
    private static String safeAttr(String s) { return (s == null) ? "" : s.trim(); }
    private static boolean notEmpty(String s) { return s != null && !s.isBlank(); }
    private static String firstNonEmpty(String... vals) {
        for (String v : vals) if (notEmpty(v)) return v;
        return null;
    }

    // Best-effort extract of visible text for buttons
    private static String safeInnerText(WebElement el) {
        try { return el.getText() == null ? "" : el.getText().trim(); }
        catch (Exception ignore) { return ""; }
    }
}