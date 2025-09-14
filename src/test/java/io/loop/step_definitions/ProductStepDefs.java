package io.loop.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.loop.pages.POM;
import io.loop.utilities.ConfigurationReader;
import io.loop.utilities.Driver;
import org.apache.logging.log4j.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ProductStepDefs {


    POM pages = new POM();
    private static final Logger LOG = LogManager.getLogger();

    @Given("User is on the HomePage")
    public void user_is_on_the_home_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("product.url"));
        LOG.info("User is on Homepage");
    }
    @Then("User should be able to see expected prices in the following products")
    public void user_should_be_able_to_see_expected_prices_in_the_following_products(List<Map<String, String>> productDetails) {

        for (Map<String, String> productDetail : productDetails) {
//            System.out.println("============Product Details===============");
//            System.out.println("productDetail.get(\"Category\") = " + productDetail.get("Category"));
//            System.out.println("productDetail.get(\"Product\") = " + productDetail.get("Product"));
//            System.out.println("productDetail.get(\"expectedPrice\") = " + productDetail.get("expectedPrice"));


            // click the category
            pages.getProductPage().clickCategory(productDetail.get("Category"));
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));

            // get actual price
            String actualPrice = pages.getProductPage().getProductPrice(productDetail.get("Product"));


            //get product expected price from table
            String expectedPrice = productDetail.get("expectedPrice");


            // do assertion
            assertEquals("Expected does not match the actual", expectedPrice, actualPrice);
            LOG.info("Validation of the price for: " + productDetail.get("Category") + ", for Product: " + productDetail.get("Product") + " expected: " + expectedPrice + " - actual: " + actualPrice);
        }

    }
}


