package com.besandr.model.properties;


import java.math.BigDecimal;

public class InitialPrice implements Property {

    private BigDecimal initialPrice;

    public InitialPrice(Object initialPrice) {
        if (initialPrice instanceof BigDecimal) {
            this.initialPrice = (BigDecimal) initialPrice;
        }
    }

    public BigDecimal getInitialPrice() {
        if (initialPrice != null) {
            return initialPrice;
        } else { return null; }
    }

    @Override
    public String toString() {
        if (initialPrice != null) {
            return initialPrice.toString();
        } else { return "";}
    }

    @Override
    public String getName() {
        return "initialPrice";
    }
}
