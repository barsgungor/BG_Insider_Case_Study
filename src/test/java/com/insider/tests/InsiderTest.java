package com.insider.tests;
import com.insider.pages.CareersPage;
import com.insider.pages.HomePage;
import com.insider.pages.QaPage;
import com.insider.utilities.BrowserUtils;
import com.insider.utilities.ConfigurationReader;
import com.insider.utilities.Driver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InsiderTest {

    HomePage homePage = new HomePage();
    CareersPage careersPage = new CareersPage();
    QaPage qaPage = new QaPage();

    @Test
    @Order(1)
    public void testHomePage(){
        //1. Verify successful loading of the Insider home page
        Driver.getDriver().get(ConfigurationReader.getProperty("base_url"));
        assertTrue(Driver.getDriver().getTitle().contains("#1 Leader in Individualized, Cross-Channel CX — Insider"), "Failed to check for the title of the Insider home page");
    }

    @Test
    @Order(2)
    public void testCareersPage(){
        //2. Confirm visibility of Locations, Teams and Life at Insider blocks on the Careers page
        Driver.getDriver().get(ConfigurationReader.getProperty("base_url"));
        homePage.companyMenu.click();
        homePage.careersOption.click();
        assertTrue(Driver.getDriver().getTitle().contains("Insider Careers"), "Failed to check for the title of the Careers page");
        assertTrue(BrowserUtils.isElementPresent(careersPage.locationsSection), "Locations section is not visible on the Careers page");
        assertTrue(BrowserUtils.isElementPresent(careersPage.teamsSection), "Teams section is not visible on the Careers page");
        assertTrue(BrowserUtils.isElementPresent(careersPage.lifeAtInsiderSection), "Life at Insider section is not visible on the Careers page");
    }

    @Test
    @Order(3)
    public void testQaPage(){
        //3. Check for Quality Assurance job listings
        Driver.getDriver().get(ConfigurationReader.getProperty("careers_url"));
        assertTrue(Driver.getDriver().getTitle().contains("Insider quality assurance job opportunities"),  "Failed to check for the title of QA job listings");
        BrowserUtils.clickWithJS(qaPage.seeAllJobsButton);
        qaPage.selectLocation("Istanbul, Turkey");
        qaPage.selectDepartment("Quality Assurance");
        assertTrue(BrowserUtils.isElementPresent(qaPage.jobsList), "Quality Assurance job listings are not visible");

        //4. Ensure each job's Position, Department, and Location contain the expected values
        assertTrue(qaPage.verifyAllJobsAttributes(), "Failed to verify Quality Assurance job attributes");

        //5. Click the “View Role” button for a job and verify redirection to the Lever Application form page
        assertTrue(qaPage.verifyViewRoleButtonRedirectsToFormPage(), "Failed to verify redirection to the Lever Application form page");
    }

    @AfterEach
    public void tearDown(){
        Driver.closeDriver();
    }

}
