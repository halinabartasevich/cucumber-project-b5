package io.loop.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {
    // creating a private constructor we are closing access to the object of this class from outside the class

    private Driver() {

    }

    // making our driver instance private, so it is not reachable outside the class
    // we make it static because we want it to run before everything else, and we will use it in static method

    private static WebDriver driver;


    // creating a reusable method that will run the same driver instance every time we call it

    /**
     * Singleton Patter
     * @return driver
     */

    public static WebDriver getDriver() {
        if (driver == null) {
            String browserType = ConfigurationReader.getProperty("browser");
            ChromeOptions options = new ChromeOptions();
            switch (browserType.toLowerCase()) {
                case "chrome" -> {
                    options.addArguments("--disable-blink-features=AutomationControlled");
                    driver = new ChromeDriver(options);
                }
                case "firefox" -> driver = new FirefoxDriver();
                case "safari" -> driver = new SafariDriver();
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    /**
     * Closing the driver
     * @author Halina
     */

    public static void closeDriver() {
        if (driver != null) {
            driver.close();
            driver = null; // we assign it back to null so that next time we can use it an assign
        }
    }
}