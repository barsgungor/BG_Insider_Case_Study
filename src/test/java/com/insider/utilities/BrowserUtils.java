package com.insider.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserUtils {

    public static void moveToElementJS(WebElement element){
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void clickWithJS(WebElement element) {
        moveToElementJS(element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    public static void waitForPresenceOfElement(By by) {
        new WebDriverWait(Driver.getDriver(), 15).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static boolean isElementPresent(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 15);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
