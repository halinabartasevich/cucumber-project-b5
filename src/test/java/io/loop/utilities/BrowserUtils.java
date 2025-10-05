package io.loop.utilities;

import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;

import static org.junit.Assert.assertTrue;


public class BrowserUtils {

    public static Scenario myScenario;

    /**
     * takes screenshot
     */
    public static void takeScreenshot(){
        try{
            myScenario.log("Current url is: " + Driver.getDriver().getCurrentUrl());
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            myScenario.attach(screenshot, "image/png", myScenario.getName());
        }catch (WebDriverException | ClassCastException wbd){
            wbd.getMessage();
        }
    }
//    public static WebDriver driver;
//    public static WebDriverWait wait;

    // this class is created to store utils for browser

    /**
     * validate if the driver switched to the expected url or title
     * @param driver
     * @param  expectedUrl
     * @param expectedTitle
     * implements assertion
     */

    public static void switchWindowAndValidate(WebDriver driver, String expectedUrl, String expectedTitle) {
        // to lower case the params to avoid case sensitiveness
        expectedTitle = expectedTitle.toLowerCase();
        expectedUrl = expectedUrl.toLowerCase();

        // get all window handles, switch one by one and each ti,e check if the url matches expected to stop
        var windowHandles = driver.getWindowHandles();
        for (var windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
            if (driver.getCurrentUrl().toLowerCase().contains(expectedUrl)) {
                break;
            }
        }

        assertTrue(driver.getTitle().toLowerCase().contains(expectedTitle));
    }

    /**
     * @param driver
     * @param targetTitle
     */

    public static void switchToWindow(WebDriver driver, String targetTitle) {
        String currentWindowHandle = driver.getWindowHandle();
        for (String each : driver.getWindowHandles()){
            driver.switchTo().window(each);
            if(driver.getTitle().contains(targetTitle)){
                return;
            }
        }
        driver.switchTo().window(currentWindowHandle);
    }

    /**
     * clicks any link from loop practice
     * @param nameOfPage
     * @author Polina
     */

    public static void loopLinkClickMethod(String nameOfPage) {
        WebElement element = Driver.getDriver().findElement(By.xpath("//a[.='" + nameOfPage + "']"));
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    /**
     * waits for the provided element to be clickable
     * @param element
     * @param timeout
     * @return element
     * @author Polina
     */

    public static WebElement waitForClickable(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    /**
     * waits for provided element to be invisible on the page
     * @param element
     * @param timeout
     */

    public static void waitForInvisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * waits for provided element to be clickable
     * @param element
     * @param timeout
     */

    public static WebElement waitForVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * uploads file for WINDOWS
     * @param filePath
     */
    public static void uploadFileForWindows (String filePath) throws AWTException {
        // copy the file path
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        // simulate keyboard pase and enter;
        Robot robot = new Robot();
        robot.delay(1000);

        //press CTRL + V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);


        // press ENTER
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }

    /**
     * uploads file for MAC
     * @param filePath
     */
    public static void  uploadFileForMac(String filePath) throws AWTException {
        Robot robot = new Robot();

        //copy the file path
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        robot.delay(1000);

        // press ⌘ + Shift + G to open go to finder
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_META);

        // Paste file path (⌘ + V)
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_META);

        robot.delay(1000);

        // press enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        robot.delay(1000);

        // Press Enter again to confirm file selection
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }

    /**
     * uploads file for MAC
     * @param filePath
     */
    public static void uploadFileUsingAppleScript(String filePath) throws Exception {
        String script = "tell application \"System Events\"\n"
                + "delay 1\n"
                + "keystroke \"G\" using {command down, shift down}\n"
                + "delay 1\n"
                + "keystroke \"" + filePath + "\"\n"
                + "keystroke return\n"
                + "delay 1\n"
                + "keystroke return\n"
                + "end tell";

        String[] command = { "osascript", "-e", script };
        Runtime.getRuntime().exec(command);
    }



}






















