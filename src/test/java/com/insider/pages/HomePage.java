package com.insider.pages;

import com.insider.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[contains(text(),'Company')]")
    public WebElement companyMenu;

    @FindBy(xpath = "//a[@href='https://useinsider.com/careers/']")
    public WebElement careersOption;

}
