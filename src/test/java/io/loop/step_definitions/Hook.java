package io.loop.step_definitions;

import io.cucumber.java.*;
import io.loop.utilities.BrowserUtils;
import io.loop.utilities.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

//import static io.cucumber.jsonformatter.CucumberJvmJson.JvmElementType.scenario;

public class Hook {
    @Before
    public void setUp(Scenario scenario) {
        Driver.getDriver();
        BrowserUtils.myScenario = scenario;
    }

    @After
    public void tearDown(Scenario scenario) {
        // only takes screenshot when scenario is failed
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        Driver.closeDriver();
    }

    //@AfterStep
    public void takeScreenshotAfterStep(Scenario scenario) {
        final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());
    }
}