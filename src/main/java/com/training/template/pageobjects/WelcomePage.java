package com.training.template.pageobjects;

import com.training.template.enums.How;
import com.training.template.util.BasePageObject;
import com.training.template.util.WebObject;
import org.openqa.selenium.WebDriver;

public class WelcomePage extends BasePageObject {

    private WebObject lblBanner = WebObject.setElement(How.XPATH, "//h2[text()='Account Services']");
    private WebObject lnkLogout = WebObject.setElement(How.XPATH, "//a[text()='Log Out']");

    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isBannerDisplayed() {
        return lblBanner.isDisplayed();
    }

    public HomePage clickLogout() {
        lnkLogout.click();
        return new HomePage(driver);
    }
}
