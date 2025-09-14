package io.loop.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class POM {

    private LoginPage loginPage;
    private GoogleSearchPage googleSearchPage;
    private HomePage homePage;
    private ProductPage productPage;

    public LoginPage getLoginPage() {
        if (loginPage == null){
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public GoogleSearchPage getGoogleSearchPage() {
        if (googleSearchPage == null){
            googleSearchPage = new GoogleSearchPage();
        }
        return googleSearchPage;
    }

    public HomePage getHomePage() {
        if(homePage == null){
            homePage = new HomePage();
        }
        return homePage;
    }

    public ProductPage getProductPage() {
        if(productPage == null){
            productPage = new ProductPage();
        }
        return productPage;
    }
}