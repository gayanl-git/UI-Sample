package com.training.template.util;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePageObject {

    public static WebDriver driver;

    public BasePageObject(WebDriver driver) {
        this.driver = driver;
    }

    protected boolean isElementDisplayed(WebElement element) {

        try {
            return element.isDisplayed();

        } catch (StaleElementReferenceException e) {
            return false;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    //Click Method
    protected void click(WebElement element) {

        try {
            waitVisibility(element);
            element.click();
        } catch (ElementClickInterceptedException e) {
            e.printStackTrace();
        }
    }

    //Write Text
    protected void writeText(WebElement element, String text) {
        waitVisibility(element);
        element.sendKeys(text);
    }

    //Write Text
    protected void clearAndWriteText(WebElement element, String text) {
        waitVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    //Read Text
    protected String readText(WebElement element) {
        waitVisibility(element);
        return element.getText();
    }

    //Wait
    protected void waitVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
