package io.loop.utilities;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PageObjectGenerator {

    public static void main(String[] args) throws IOException {


        String url = "https://beta.docuport.app";

        Driver.getDriver().get(url); // Replace with your target URL

        StringBuilder sb = new StringBuilder();
        sb.append("package io.loop.pages;\n\n")
                .append("import org.openqa.selenium.WebElement;\n")
                .append("import org.openqa.selenium.support.FindBy;\n")
                .append("import org.openqa.selenium.support.PageFactory;\n")
                .append("import io.loop.utilities.Driver;\n\n")
                .append("public class GoogleSearchPage {\n\n")
                .append("    public GoogleSearchPage() {\n")
                .append("        PageFactory.initElements(Driver.getDriver(), this);\n")
                .append("    }\n\n");

        List<WebElement> elements = Driver.getDriver().findElements(By.xpath("//input[@id or @name or @placeholder or @aria-label]"));

        for (WebElement el : elements) {
            String id = el.getAttribute("id");
            String name = el.getAttribute("name");
            String placeholder = el.getAttribute("placeholder");
            String aria = el.getAttribute("aria-label");

            String identifier = (id != null && !id.isEmpty()) ? "id: " + id :
                    (name != null && !name.isEmpty()) ? "name: " + name :
                            (placeholder != null && !placeholder.isEmpty()) ? "placeholder: " + placeholder :
                                    (aria != null && !aria.isEmpty()) ? "aria-label: " + aria : "input field";

            String variableName = toCamelCase(placeholder != null ? placeholder :
                    aria != null ? aria :
                            name != null ? name :
                                    id != null ? id : "input");

            // Prioritize the most stable locator
            if (id != null && !id.isEmpty()) {
                sb.append("    @FindBy(id = \"").append(id).append("\")\n");
            } else if (name != null && !name.isEmpty()) {
                sb.append("    @FindBy(name = \"").append(name).append("\")\n");
            } else if (placeholder != null && !placeholder.isEmpty()) {
                sb.append("    @FindBy(xpath = \"//input[@placeholder='").append(placeholder).append("']\")\n");
            } else if (aria != null && !aria.isEmpty()) {
                sb.append("    @FindBy(xpath = \"//input[@aria-label='").append(aria).append("']\")\n");
            }

            sb.append("    public WebElement ").append(variableName).append(";\n\n");
        }

        sb.append("}\n");

        FileWriter writer = new FileWriter("src/test/java/io/loop/pages/Sample.java");
        writer.write(sb.toString());
        writer.close();

        Driver.getDriver().quit();
        System.out.println("✅ Sample.java generated successfully!");
    }

    // Converts kebab-case, snake_case, or spaced strings to camelCase
    private static String toCamelCase(String input) {
        if (input == null || input.isEmpty()) return "element";

        input = input.replaceAll("[^a-zA-Z0-9 ]", " ").replaceAll("[_\\-]", " ");// Fixed escaping

        String[] parts = input.trim().split("\\s+");
        StringBuilder camelCaseString = new StringBuilder(parts[0].toLowerCase());

        for (int i = 1; i < parts.length; i++) {
            camelCaseString.append(parts[i].substring(0, 1).toUpperCase())
                    .append(parts[i].substring(1).toLowerCase());
        }

        return camelCaseString.toString();
    }
}