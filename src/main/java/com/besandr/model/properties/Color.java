package com.besandr.model.properties;

public class Color implements Property {

    private String color;

    public Color(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }

    @Override
    public String getName() {
        return "color";
    }
}
