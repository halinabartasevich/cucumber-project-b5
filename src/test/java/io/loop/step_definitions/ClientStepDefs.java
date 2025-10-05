package io.loop.step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.pages.POM;
import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DocuportConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;


public class ClientStepDefs {

    private Logger LOG = LogManager.getLogger();
        private  SoftAssertions softAssertions = new SoftAssertions();
        POM pages = new POM();


    @Then("user validates {string} text is displayed")
    public void user_validates_text_is_displayed(String text) {

        String actual;
        String expected;

        switch (text.toLowerCase().trim()){
            case  "login"-> {
               actual = pages.getLoginPage().loginText.getText().toLowerCase().trim();
              //  actual = "nadir";    --> if we want to see an issue in console
                expected = text.toLowerCase().trim();
                softAssertions.assertThat(actual).isEqualTo(expected);
                LOG.info(text + " is displayed");
            }
            case "docuport" -> {
            actual = pages.getLoginPage().docuportText.getAttribute("alt").toLowerCase().trim();
                // actual = "halina";  --> if we want to see an issue in console
            expected = text.toLowerCase().trim();
            softAssertions.assertThat(actual).isEqualTo(expected);
            LOG.info(text + " is displayed");
            }

            case "choose account" -> {
                BrowserUtils.waitForInvisibility(pages.getLoginPage().chooseAccountText, DocuportConstants.LARGE);
                actual = pages.getLoginPage().chooseAccountText.getText().toLowerCase().trim();
                expected = text.toLowerCase().trim();
                softAssertions.assertThat(actual).isEqualTo(expected);
                LOG.info(text + " is displayed");

            }
            default -> throw new IllegalArgumentException("Not such a text: " + text);
        }


    }



    @When("user validates all assertions")
    public void user_validates_all_assertions() {
        softAssertions.assertAll();

    }
}
