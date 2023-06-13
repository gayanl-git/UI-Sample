package com.training.template.tests;

import com.training.template.listeners.Retry;
import com.training.template.pageobjects.HomePage;
import com.training.template.pageobjects.WelcomePage;
import com.training.template.reporting.ExtentTestManager;
import com.training.template.util.BaseTestObject;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.lang.reflect.Method;

public class TestClassOne extends BaseTestObject {

    private HomePage homePage;
    private WelcomePage welcomePage;

    @BeforeClass
    public void testSetUp() {

        try {
            driver.get(testData.get("baseUrl"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCaseValidUser(Method method) {

        try {
            ExtentTestManager.startTest(method.getName(), "Valid Login Scenario with invalid username and password.");

            homePage = new HomePage(driver);
            homePage.enterUserName(testData.get("ValidUserName"));
            homePage.enterPassword(testData.get("ValidPassword"));
            welcomePage = homePage.clickLogin();
            Assert.assertTrue(welcomePage.isBannerDisplayed());

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Test(dependsOnMethods = {"testCaseValidUser"})
    public void testCaseLogout(Method method) {

        try {
            ExtentTestManager.startTest(method.getName(), "Scenario User Logout");

            Assert.assertTrue(welcomePage.isBannerDisplayed());
            welcomePage.clickLogout();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
