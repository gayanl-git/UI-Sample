package com.training.template.enums;

public enum Browser {

    CHROME("chrome"), FIREFOX("firefox");

    private String value;

    Browser(String browser) {
        this.value = browser;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }


}
