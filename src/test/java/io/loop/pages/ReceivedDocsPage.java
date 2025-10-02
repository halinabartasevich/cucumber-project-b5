package io.loop.pages;

import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReceivedDocsPage {
    public ReceivedDocsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
     @FindBy(xpath = "//span[@class='subtitle-2 text-none' and .='Search']")
    //@FindBy(xpath = "(//span[.='Search'])[2]")
    public WebElement searchButton;


    @FindBy(xpath = "//label[.='Document name']/following-sibling::input")
    public WebElement documentName;

    public void clickButton(String button) {

        switch(button.toLowerCase().trim()) {
            case "search" -> BrowserUtils.waitForClickable(searchButton, DocuportConstants.LARGE).click();
            default -> throw new IllegalArgumentException("No such button: " + button);
        }
    }
    public void insertField (String field, String input) {
        switch (field.toLowerCase().trim()) {
           case "document name" -> BrowserUtils.waitForVisibility(documentName, DocuportConstants.LARGE).sendKeys(input);
            default -> throw new IllegalArgumentException("No such a field: " + field);
        }
    }
}