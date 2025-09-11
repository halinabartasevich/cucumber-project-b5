package io.loop.pages;

import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (xpath="//img[@alt='Docuport']")
    public WebElement logo;


    @FindBy (xpath="//button[@type='submit']")
    public WebElement continueButton;


}