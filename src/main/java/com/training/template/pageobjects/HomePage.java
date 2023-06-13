package com.training.template.pageobjects;

import com.training.template.util.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePageObject {

    @FindBy(name = "username")
    @CacheLookup
    private WebElement txtUserName;

    @FindBy(name = "password")
    @CacheLookup
    private WebElement txtPassword;

    @FindBy(how = How.XPATH, using = "//input[@value='Log In']")
    private WebElement btnLogin;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterUserName(String userName){
        writeText(txtUserName, userName);
    }

    public void enterPassword(String password){
        clearAndWriteText(txtPassword, password);
    }

    public WelcomePage clickLogin(){
        click(btnLogin);
        return new WelcomePage(driver);
    }
}
