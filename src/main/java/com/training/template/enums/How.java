package com.training.template.enums;

public enum How {

    ID("id"), NAME("name"), XPATH("xpath");

    private String value;

    How(String locator) {
        this.value = locator;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }


}
