package com.besandr.model.properties;

public class Description implements Property {

    private String description;

    public Description (String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
