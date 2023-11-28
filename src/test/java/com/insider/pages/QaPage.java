package com.insider.pages;

import com.insider.utilities.BrowserUtils;
import com.insider.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class QaPage {

    public QaPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[.='See all QA jobs']")
    public WebElement seeAllJobsButton;

    @FindBy(css = "div#jobs-list")
    public WebElement jobsList;

    @FindBy(css = "select#filter-by-location")
    private WebElement locationFilter;

    @FindBy(css = "select#filter-by-department")
    private WebElement departmentFilter;

    @FindBy(css = "p.position-title.font-weight-bold")
    private List<WebElement> openPositionsTitles;

    @FindBy(css = "span.position-department.text-large.font-weight-600.text-primary")
    private List<WebElement> openPositionsDepartments;

    @FindBy(css = "div.position-location.text-large")
    private List<WebElement> openPositionsLocations;

    @FindBy(xpath = "//a[.='View Role']")
    private List<WebElement> viewRoleButtons;

    public void selectLocation(String location){
        Select select = new Select(locationFilter);
        select.selectByVisibleText(location);
    }

    public void selectDepartment(String department){
        Select select = new Select(departmentFilter);
        select.selectByVisibleText(department);
    }

    public boolean verifyAllJobsAttributes(){
        BrowserUtils.waitForPresenceOfElement(By.xpath("//p[contains(text(),'Quality Assurance')]"));

        for(WebElement eachTitle : openPositionsTitles){
            if(!(eachTitle.getAttribute("innerHTML").contains("Quality Assurance") || eachTitle.getAttribute("innerHTML").contains("QA"))){
                return false;
            }
        }

        for(WebElement eachDepartment : openPositionsDepartments){
            if(!eachDepartment.getAttribute("innerHTML").contains("Quality Assurance")){
                return false;
            }
        }

        for(WebElement eachLocation : openPositionsLocations){
            if(!eachLocation.getAttribute("innerHTML").contains("Istanbul, Turkey")){
                return false;
            }
        }

        return true;
    }

    public boolean verifyViewRoleButtonRedirectsToFormPage(){
        String firstTab = Driver.getDriver().getWindowHandle();

        for(WebElement eachButton : viewRoleButtons){
            BrowserUtils.clickWithJS(eachButton);

            String nextTab = Driver.getDriver().getWindowHandles().stream().reduce((first, second) -> second).get();
            Driver.getDriver().switchTo().window(nextTab);

            BrowserUtils.waitForPresenceOfElement(By.xpath("//a[.='Apply for this job']"));

            if(!Driver.getDriver().getCurrentUrl().contains("https://jobs.lever.co/")){
                return false;
            }
            Driver.getDriver().switchTo().window(firstTab);
        }

        return true;
    }


}
