package io.loop.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.pages.HomePage;
import io.loop.pages.LoginPage;
import io.loop.utilities.BrowserUtils;
import io.loop.utilities.ConfigurationReader;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class LoginStepDefs {

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    @Given("user is on Docuport login page")
    public void user_is_on_docuport_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperties("docuportBETA"));

    }

    @When("user enters username for clien")
    public void user_enters_username_for_clien() {
        //Brows //BrowserUtils.waitForClickable(loginPage.loginButton, 10); - it is no good
        BrowserUtils.waitForClickable(loginPage.loginButton, DocuportConstants.LARGE);
        assertTrue("Login buton is NOT displayed", loginPage.loginButton.isDisplayed());
        loginPage.userNameInput.sendKeys(DocuportConstants.USERNAME_CLIENT);
    }

    @When("user enters password for client")
    public void user_enters_password_for_client() {
        loginPage.passwordInput.sendKeys(DocuportConstants.PASSWORD);
    }

    @When("use clicks login")
    public void use_clicks_login() {
        loginPage.loginButton.click();
    }

    @Then("user should be able to see the home page for client")
    public void user_should_be_able_to_see_the_home_page_for_client() throws InterruptedException {
        Thread.sleep(3000);
        homePage.continueButton.click();
        assertTrue("Home page is not loaded", homePage.logo.isDisplayed());
    }


    @When("user enters username for employee")
    public void user_enters_username_for_employee() {

    }

    @When("user enters password for employee")
    public void user_enters_password_for_employee() {

    }

    @Then("user should be able to see the home page for employee")
    public void user_should_be_able_to_see_the_home_page_for_employee() {

    }


    @When("user enters username for advisor")
    public void user_enters_username_for_advisor() {

    }

    @When("user enters password for advisor")
    public void user_enters_password_for_advisor() {

    }

    @Then("user should be able to see the home page for advisor")
    public void user_should_be_able_to_see_the_home_page_for_advisor() {

    }


    @When("user enters username for supervisor")
    public void user_enters_username_for_supervisor() {

    }

    @When("user enters password for supervisor")
    public void user_enters_password_for_supervisor() {

    }

    @Then("user should be able to see the home page for supervisor")
    public void user_should_be_able_to_see_the_home_page_for_supervisor() {

    }


    @When("user enters credentials")
    public void user_enters_credentials(Map<String, String> credentials) throws InterruptedException {

        for (Map.Entry<String, String> entry : credentials.entrySet()) {
            String key = entry.getKey();
            System.out.println("key = " + key);

            String value = entry.getValue();
            System.out.println("value = " + value);

            System.out.println("************************");
        }
        //loginPage.login((credentials.get("username")), credentials.get("password"));
//        loginPage.docuportLogin(credentials.get("username"), credentials.get("password"));
        loginPage.login(credentials.get("userNameInput"), credentials.get("passwordInput"));


    }
}