package com.interview.model.properties;

public class Brand implements Property {

    private String brand;

    public Brand (String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return brand;
    }
}
