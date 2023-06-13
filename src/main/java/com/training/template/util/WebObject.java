package com.training.template.util;

import com.training.template.enums.How;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebObject implements WebElement {

    private How how;
    private String locatorValue;
    private static WebDriver webDriver;

    private WebObject(How how, String locatorValue) {
        this.how = how;
        this.locatorValue = locatorValue;
    }

    public static WebObject setElement(How how, String locatorValue){
        return new WebObject(how, locatorValue);
    }

    public static void setDriver(WebDriver driver){
        webDriver = driver;
    }

    private WebElement getElement(){
        switch (how){
            case ID:
                return webDriver.findElement(By.id(locatorValue));
            case NAME:
                return webDriver.findElement(By.name(locatorValue));
            case XPATH:
                return webDriver.findElement(By.xpath(locatorValue));
            default:
                throw new IllegalStateException("Unexpected value: " + how);
        }
    }

    public void click() {

        try {
            waitVisibility();
            getElement().click();
        } catch (ElementClickInterceptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void submit() {

    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {

    }

    @Override
    public void clear() {

    }

    @Override
    public String getTagName() {
        return null;
    }

    @Override
    public String getAttribute(String name) {
        return null;
    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return null;
    }

    @Override
    public WebElement findElement(By by) {
        return null;
    }

    //Write Text
    public void writeText(String text) {
        waitVisibility();
        getElement().sendKeys(text);
    }

    //Write Text
    public void clearAndWriteText(String text) {
        waitVisibility();
        getElement().clear();
        getElement().sendKeys(text);
    }

    //Read Text
    public String readText() {
        waitVisibility();
        return getElement().getText();
    }

    //Wait
    public void waitVisibility() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(getElement()));
    }

    public boolean isDisplayed() {

        try {
            return getElement().isDisplayed();

        } catch (StaleElementReferenceException e) {
            return false;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public Point getLocation() {
        return null;
    }

    @Override
    public Dimension getSize() {
        return null;
    }

    @Override
    public Rectangle getRect() {
        return null;
    }

    @Override
    public String getCssValue(String propertyName) {
        return null;
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }
}
