package com.example.as3.model;

public enum Color {
    RED("Red"),
    BLUE("Blue"),
    BLACK("Black"),
    YELLOW("Yellow"),
    GREEN("Green");

    private final String displayValue;

    Color(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    @Override public String toString() { return displayValue; }
}
