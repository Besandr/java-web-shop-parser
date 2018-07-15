package com.interview.model.properties;

public class Name implements Property {

    private String name;

    public Name(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
