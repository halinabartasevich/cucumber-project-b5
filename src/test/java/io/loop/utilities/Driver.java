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

    // private static WebDriver driver;
    // implement threadLocal to achieve multi thread locally
    private static InheritableThreadLocal <WebDriver> driverPool = new InheritableThreadLocal<>();

    /*
    Creating a reusable method that will return the same driver instance every time when we call it
     */

    /**
     * Singleton pattern
     * @return
     */
    public static WebDriver getDriver() {
        if (driverPool.get() == null) {
            String browserType = ConfigurationReader.getProperties("browser");
            //String browserType = System.getenv("browser");

            ChromeOptions options = new ChromeOptions();
            switch (browserType.toLowerCase()) {
                case "chrome" -> {
                    options.addArguments("--disable-blink-features=AutomationControlled");
                   options.addArguments("--disable-blink-features=AutomationControlled");
                    options.addArguments("--disable-password-manager-reauthentication");
                    options.addArguments("--disable-features=PasswordLeakDetection,PasswordManagerOnboarding");
                    options.setExperimentalOption("prefs", new java.util.HashMap<String, Object>() {{
                        put("credentials_enable_service", false);
                        put("profile.password_manager_enabled", false);
                        put("profile.password_manager_leak_detection", false);
                        put("autofill.profile_enabled", false);
                        put("autofill.credit_card_enabled", false);
                    }});
                    driverPool.set(new ChromeDriver(options));
                }
                case "firefox" -> driverPool.set(new FirefoxDriver());
                case "safari" -> driverPool.set(new SafariDriver());
                case "headless" -> {
                    options.addArguments("--disable-blink-features=AutomationControlled");
                    options.addArguments("--headless");
                    driverPool.set(new ChromeDriver(options));

                }
            }
            driverPool.get().manage().window().maximize();
            //driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.valueOf(ConfigurationReader.getProperties("timeouts"))));
            driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(DocuportConstants.LARGE));
        }
        return driverPool.get();
    }

    /**
     * Closing the driver
     * @author nsh
     */
    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
