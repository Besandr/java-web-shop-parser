package com.besandr.model.properties;


import java.math.BigDecimal;

public class Price implements Property {

    private BigDecimal price;

    public Price(Object price) {
        if (price instanceof BigDecimal) {
            this.price = (BigDecimal) price;
        }

    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return price.toString();
    }

    @Override
    public String getName() {
        return "price";
    }
}
