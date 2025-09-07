package io.loop.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class DocuportUtils {
    /**
     * logins to docuport app
     * @param driver, which is initialized in the test base
     * @param role, comes from docuport constants
     * author PolinaRostkova
     */
    public static void login(WebDriver driver, String role) throws InterruptedException {
        driver.get(ConfigurationReader.getProperty("docuportBETA"));
        WebElement username = driver.findElement(By.xpath("//label[.='Username or email']/following-sibling::input"));
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

        switch (role.toLowerCase()) {
            case "client":
                username.sendKeys(DocuportConstants.USERNAME_CLIENT);
                password.sendKeys(DocuportConstants.PASSWORD);
                break;
            case "supervisor":
                username.sendKeys(DocuportConstants.USERNAME_SUPERVISOR);
                password.sendKeys(DocuportConstants.PASSWORD);
                break;
            case "advisor":
                username.sendKeys(DocuportConstants.USERNAME_ADVISOR);
                password.sendKeys(DocuportConstants.PASSWORD);
                break;
            case "employee":
                username.sendKeys(DocuportConstants.USERNAME_EMPLOYEE);
                password.sendKeys(DocuportConstants.PASSWORD);
                break;
            default:
                throw new IllegalArgumentException("Invalid role");
        }


        loginButton.click();

        if (role.toLowerCase().equals("client")) {
            Thread.sleep(3000);
            WebElement continueButton = driver.findElement(By.xpath("//button[@type='submit']"));
            continueButton.click();
            Thread.sleep(3000);
        }
    }

    public static void logOut(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement logoutButton = driver.findElement(By.xpath("//div[@class='v-avatar primary']"));
        logoutButton.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement logoutLink = driver.findElement(By.xpath("//span[contains(text(),'Log out')]"));
        logoutLink.click();
    }

    /**
     * @param driver
     * @param emailAdress
     * @param field
     * @return
     * @author Polina
     */

    public static String returnAnyFieldFromTable(WebDriver driver, String emailAdress, String field) {
        WebElement element = null;
        String xpath = "";

        element = switch (field.toLowerCase()) {
            case "full name" -> {
                xpath = "//td[2][text()='" + emailAdress + "']/preceding-sibling::td//span/following-sibling::span";
                yield driver.findElement(By.xpath(xpath));
            }
            case "username" -> {
                xpath = "//td[2][text()='" + emailAdress + "']/following-sibling::td[1]";
                yield driver.findElement(By.xpath(xpath));
            }
            case "inviter" -> {
                xpath = "//td[2][text()='" + emailAdress + "']/following-sibling::td[2]";
                yield driver.findElement(By.xpath(xpath));
            }
            case "phone number" -> {
                xpath = "//td[2][text()='" + emailAdress + "']/following-sibling::td[3]/span";
                yield driver.findElement(By.xpath(xpath));
            }
            case "role" -> {
                xpath = "//td[2][text()='" + emailAdress + "']/following-sibling::td[4]/span/span";
                yield driver.findElement(By.xpath(xpath));
            }
            case "advisor" -> {
                xpath = "//td[2][text()='" + emailAdress + "']/following-sibling::td[5]";
                yield driver.findElement(By.xpath(xpath));
            }
            default -> throw new IllegalArgumentException("Invalid field");
        };
        return element.getText().trim();
    }
}