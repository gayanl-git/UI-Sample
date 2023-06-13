package com.training.template.constants;

public interface Constants {

    // Sauce Labs Connection Data
    String SAUCELABS_USERNAME = "";
    String SAUCELABS_ACCESS_KEY = "";
    String SAUCELABS_URL = "http://" + SAUCELABS_USERNAME + ":" + SAUCELABS_ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
    String DEFAULT_CONFIG_LOCATION = "src/test/resources/config/config.cfg";
}
