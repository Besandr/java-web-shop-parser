package com.besandr.model.properties;

public class Size implements Property {

   private String size;

    public Size (String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return size;
    }

    @Override
    public String getName() {
        return "size";
    }
}
