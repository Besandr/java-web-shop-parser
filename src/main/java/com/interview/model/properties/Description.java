package com.interview.model.properties;

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
