package io.loop.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.pages.GoogleSearchPage;
import io.loop.utilities.ConfigurationReader;
import io.loop.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class GoogleSearchStepDefs {

    GoogleSearchPage googleSearchPage = new GoogleSearchPage();

    @Given("user is on Google page")
    public void user_is_on_Google_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("google.url"));
    }


    @When("user types Loop Academy in the google search box and clicks enter")
    public void user_types_Loop_Academy_in_the_google_search_box_and_clicks_enter() {
        googleSearchPage.searchBox.sendKeys("Loop Academy" + Keys.ENTER);
    }

    @Then("user should be able to see Loop Academy - Google search in the google title")
    public void user_should_be_able_to_see_Loop_Academy_Google_search_in_the_google_title() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("Loop Academy - Google Search"));
        String actual = Driver.getDriver().getTitle();
        assertEquals("Expected does NOT match actual", "Loop Academy - Google Search", actual);
    }

    //-----------------------------------------------------

    @When("user types {string} in the google search box and clicks enter")
    public void user_types_in_the_google_search_box_and_clicks_enter(String input) {
        googleSearchPage.searchBox.sendKeys(input + Keys.ENTER);
    }
    @Then("user should be able to see {string} in the google title")
    public void user_should_be_able_to_see_in_the_google_title(String expectedTitle) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
//        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.titleIs(expectedTitle));
        Thread.sleep(3000);
        String actualTitle = Driver.getDriver().getTitle();
        assertEquals("Not matching", expectedTitle, actualTitle);
    }
}