package com.training.template.util;

import com.training.template.constants.Constants;
import com.training.template.enums.Browser;
import com.training.template.listeners.TestListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import java.net.URL;
import java.time.Duration;
import java.util.TreeMap;

@Listeners({TestListener.class})
public abstract class BaseTestObject {

    public static WebDriver driver;
    public static TreeMap<String, String> testData = null;
    public static final String ENVIRONMENT;
    public static final Browser BROWSER;
    public static final String SAUCELABS_STATUS;
    protected Logger log = Logger.getLogger(BaseTestObject.class);

    static {
        Config.setConfigFilePath(Constants.DEFAULT_CONFIG_LOCATION);
        DOMConfigurator.configure(Config.getProperty("logFileOutputPath"));
        ENVIRONMENT = (System.getProperty("environment") == null)? Config.getProperty("environment"): System.getProperty("environment");
        BROWSER = Browser.valueOf(System.getProperty("browser").toUpperCase());
        SAUCELABS_STATUS = System.getProperty("saucelabs");
    }

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    public void loadEnvironmentData() throws Exception {

        try {
            String path = "/testdata/" + ENVIRONMENT.toLowerCase() + "_data.properties";
            log.info("Load the properties file " + path);
            testData = new LoadDataProperties().getWebDataMapping(path);

        } catch (Exception e) {
            throw new Exception("Failed : getWebDataMapping()" + e.getLocalizedMessage());
        }
    }

    @BeforeTest
    public void createBrowser() throws Exception {

        try {
            log.info("createBrowser()");
            DriverManagerType browserType = DriverManagerType.valueOf(BROWSER.toString().toUpperCase());
            WebDriverManager.getInstance(browserType).setup();
            Class<?> browserClass =  Class.forName(browserType.browserClass());
            driver = (WebDriver) browserClass.newInstance();

            WebObject.setDriver(driver);

            // Timeouts
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(Config.getProperty("implicitWait"))));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.parseLong(Config.getProperty("pageLoadTimeout"))));

        } catch (Exception e) {
            throw new Exception("Failed : createBrowser()" + e.getLocalizedMessage());
        }
    }

    @AfterTest
    public void closeBrowser() throws Exception {

        try {
            log.info("closeBrowser()");
            if (driver != null) driver.quit();

        } catch (Exception e) {
            throw new Exception("Failed : closeBrowser()" + e.getLocalizedMessage());
        }
    }

    private void createSauceLabs(String scenarioName) throws Exception {

        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(Config.getProperty("SL_BROWSER"));
            capabilities.setVersion(Config.getProperty("SL_VERSION"));
            capabilities.setCapability(CapabilityType.PLATFORM_NAME, Config.getProperty("SL_PLATFORM"));
            capabilities.setCapability("name", scenarioName);

            System.out.println("SauceOnDemandSessionID=" + "  " + " job-name=" + scenarioName);

            driver = new RemoteWebDriver(new URL(Constants.SAUCELABS_URL), capabilities);
        } catch (Exception e) {
            throw new Exception("Failed : createSauceLabs()" + e.getLocalizedMessage());
        }

    }
}
