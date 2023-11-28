package com.insider.pages;

import com.insider.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CareersPage {

    public CareersPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "section#career-find-our-calling")
    public WebElement teamsSection;

    @FindBy(css = "section[data-id='8ab30be']")
    public WebElement locationsSection;

    @FindBy(css = "section[data-id='a8e7b90']")
    public WebElement lifeAtInsiderSection;

}
