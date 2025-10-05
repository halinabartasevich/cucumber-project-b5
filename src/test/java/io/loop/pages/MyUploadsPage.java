package io.loop.pages;

import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyUploadsPage {

        public MyUploadsPage() {PageFactory.initElements(Driver.getDriver(),this);}

            @FindBy (xpath = "//span[.='Upload documents' and @class='subtitle-2 text-none']")
            public WebElement uploadsDocuments;


            @FindBy (xpath = "//span[.='Upload file' and @class='subtitle-2 text-none']")
            public WebElement uploadFile;

            @FindBy(xpath = "//span[contains(text(),' Upload ')]")
            public WebElement upload;


    public void clickButton(String button){
        switch (button.toLowerCase().trim()){
            case "upload documents" ->  BrowserUtils.waitForClickable(uploadsDocuments, DocuportConstants.LARGE).click();
            case "upload file" ->  BrowserUtils.waitForClickable(uploadFile, DocuportConstants.MEDIUM).click();

            default -> throw new IllegalArgumentException("Not such a button: " + button);
        }
    }

        }




