package com.besandr.model.properties;

import java.util.List;

public class Sizes implements Property {
    private List<Size> sizes;

    public Sizes (Object sizes) {
        if (sizes instanceof List) {
            this.sizes = (List) sizes;
        }
    }

    @Override
    public String toString() {
        return sizes.toString();
    }

    public List<Size> getSizes(){
        return sizes;
    }
}
