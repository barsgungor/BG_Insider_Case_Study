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
        assertTrue(Driver.getDriver().getTitle().equals("#1 Leader in Individualized, Cross-Channel CX — Insider"));
    }

    @Test
    @Order(2)
    public void testCareersPage(){
        //2. Confirm visibility of Locations, Teams and Life at Insider blocks on the Careers page
        Driver.getDriver().get(ConfigurationReader.getProperty("base_url"));
        homePage.companyMenu.click();
        homePage.careersOption.click();
        assertTrue(Driver.getDriver().getTitle().equals("Insider Careers"));
        assertTrue(BrowserUtils.isElementPresent(careersPage.locationsSection));
        assertTrue(BrowserUtils.isElementPresent(careersPage.teamsSection));
        assertTrue(BrowserUtils.isElementPresent(careersPage.lifeAtInsiderSection));
    }

    @Test
    @Order(3)
    public void testQaPage(){
        //3. Check for Quality Assurance job listings
        Driver.getDriver().get(ConfigurationReader.getProperty("careers_url"));
        assertTrue(Driver.getDriver().getTitle().equals("Insider quality assurance job opportunities"));
        BrowserUtils.clickWithJS(qaPage.seeAllJobsButton);
        qaPage.selectLocation("Istanbul, Turkey");
        qaPage.selectDepartment("Quality Assurance");
        assertTrue(BrowserUtils.isElementPresent(qaPage.jobsList));

        //4. Ensure each job's Position, Department, and Location contain the expected values
        assertTrue(qaPage.verifyAllJobsAttributes());

        //5. Click the “View Role” button for a job and verify redirection to the Lever Application form page
        assertTrue(qaPage.verifyViewRoleButtonRedirectsToFormPage());
    }

    @AfterEach
    public void tearDown(){
        Driver.closeDriver();
    }

}
