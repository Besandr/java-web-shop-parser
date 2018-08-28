package com.besandr.model.properties;

import lombok.Getter;

import java.math.BigDecimal;

public class InitialPrice implements Property {

    @Getter
    private BigDecimal initialPrice;

    public InitialPrice(Object initialPrice) {
        if (initialPrice instanceof BigDecimal) {
            this.initialPrice = (BigDecimal) initialPrice;
        }
    }


    @Override
    public String toString() {
        return initialPrice.toString();
    }
}
