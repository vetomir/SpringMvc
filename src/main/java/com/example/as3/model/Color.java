package com.example.as3.model;

public enum Color {
    RED("Red"),
    BLUE("Blue"),
    BLACK("Black"),
    YELLOW("Yellow"),
    GREEN("Green");

    private String displayName;

    Color(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() { return displayName; }



    @Override public String toString() { return displayName; }
}
