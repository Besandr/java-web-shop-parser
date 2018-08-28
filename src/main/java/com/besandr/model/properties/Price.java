package com.besandr.model.properties;

import lombok.Getter;

import java.math.BigDecimal;

public class Price implements Property {

    @Getter
    private BigDecimal price;

    public Price(Object price) {
        if (price instanceof BigDecimal) {
            this.price = (BigDecimal) price;
        }

    }


    @Override
    public String toString() {
        return price.toString();
    }
}
