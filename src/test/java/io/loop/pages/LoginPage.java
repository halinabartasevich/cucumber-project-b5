package io.loop.pages;

import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.print.Doc;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (xpath = "//input[@type='text']")
    public WebElement userNameInput;

    @FindBy (xpath = "//input[@type='password']")
    public WebElement passwordInput;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement loginButton;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement continueButton;

    @FindBy(xpath = "//*[.=' Login ']" )
    public WebElement loginText;

    @FindBy(xpath = "//img[@src='/img/logo.d7557277.svg']" )
    public WebElement docuportText;


    @FindBy(xpath = "//h3[.='Choose account']" )
    public WebElement chooseAccountText;



    public void insertField(String field, String input) {
        switch(field.toLowerCase().trim()) {
            case "username" -> BrowserUtils.waitForVisibility(userNameInput, DocuportConstants.LARGE).sendKeys(input);
            case "password" -> BrowserUtils.waitForVisibility(passwordInput, DocuportConstants.LARGE).sendKeys(input);
            default -> throw new IllegalArgumentException("Not such a button");
        }
    }

    public void clickButton(String button) throws InterruptedException {
        switch (button.toLowerCase().trim()){
            case "login" -> BrowserUtils.waitForClickable(loginButton, DocuportConstants.LARGE).click();
            case "continue" -> {
                try {
                    BrowserUtils.waitForClickable(continueButton, DocuportConstants.LARGE).click();
                } catch (StaleElementReferenceException e){
                  //  Thread.sleep(5000);
                    WebElement element = Driver.getDriver().findElement(By.xpath("//span[.=' Continue ']"));
                    BrowserUtils.waitForClickable(element, DocuportConstants.EXTRA_LARGE).click();
                }
            }
            default -> throw new IllegalArgumentException("Not such a button: " + button);
        }
    }



    /**
     * Logins to docuport
     * @param username
     * @param password
     */
    public void login(String username, String password) throws InterruptedException {
        BrowserUtils.waitForClickable(loginButton, 10);
        userNameInput.clear();
        userNameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginButton.click();
        Thread.sleep(3000);
//
//        if(BrowserUtils.waitForVisibility(loginButton,10).isDisplayed()) {
//            loginButton.click();
//        }
    }




}

