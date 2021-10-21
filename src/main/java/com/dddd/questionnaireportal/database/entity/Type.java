package com.dddd.questionnaireportal.database.entity;

public enum Type {

    SINGLE_LINE_TEXT("Single line text"),
    MULTILINE_TEXT("Multiline text"),
    RADIO_BUTTON("Radio button"),
    CHECKBOX("Check box"),
    COMBOBOX("Combobox"),
    DATE("Date");

    private final String label;

    private Type(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
