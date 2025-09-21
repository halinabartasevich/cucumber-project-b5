package io.loop.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.pages.GoogleSearchPage;
import io.loop.utilities.BrowserUtils;
import io.loop.utilities.ConfigurationReader;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GoogleSearchStepDefs {

    GoogleSearchPage googleSearchPage = new GoogleSearchPage();

    @Given("user is on Home page page")
    public void user_is_on_home_page_page() {
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


    @When("user searches for {string}")
    public void user_searches_for(String country) {
        googleSearchPage.searchBox.sendKeys("What is the capital of country: " + country + Keys.ENTER);

    }

    @Then("user should see {string} in the results as capital")
    public void user_should_see_in_the_results_as_capital(String capital) {
        assertEquals("Expected capital city: " + capital + " does NOT match with actual one: " + googleSearchPage.capital.getText(), capital, googleSearchPage.capital.getText());
    }

    @Then("we love Loop Academy")
    public void we_love_loop_academy() {
        System.out.println("We love Loop Academy");
    }


    //  @Then("user searches the following items")
    //  public void user_searches_the_following_items(List<String> items) {
//       for (String item : items) {
//     googleSearchPage.searchBox.clear();
//     googleSearchPage.searchBox.sendKeys(item+ Keys.ENTER);
//           WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(DocuportConstants.LARGE));
//           wait.until(ExpectedConditions.titleIs(item + " - Google Search"));
//           assertEquals("Expected does NOT match the actual", item + " - Google Search", Driver.getDriver().getTitle());
//           BrowserUtils.takeScreenshot();

//        items.forEach(p -> {
//            googleSearchPage.searchBox.clear();
//            googleSearchPage.searchBox.sendKeys(p + Keys.ENTER);
//            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
//            wait.until(ExpectedConditions.titleIs(p + " - Google Search"));
//            assertEquals("Expected does not match the actual", p + " - Google Search", Driver.getDriver().getTitle());
//            BrowserUtils.takeScreenshot();
//        });
//  }
@Then("user searches the following items")
// LIST OF MAP
public void user_searches_the_following_items(List<Map<String, String>> items) {
    for (Map <String, String> item : items){
        System.out.println("item.get(\"items\") = " + item.get("items"));
        googleSearchPage.searchBox.clear();
        googleSearchPage.searchBox.sendKeys(item.get("items") + Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs(item.get("items") + " - Google Search"));
        assertEquals("Expected does not match the actual", item.get("items") + " - Google Search", Driver.getDriver().getTitle());
        BrowserUtils.takeScreenshot();
    }
}
    }