package com.besandr.model.properties;

import java.math.BigDecimal;

public class ShippingCost implements Property {

    private BigDecimal shippingCost;

    public ShippingCost(Object shippingCost) {
        if (shippingCost instanceof BigDecimal) {
            this.shippingCost = (BigDecimal) shippingCost;
        }
    }


    @Override
    public String toString() {
        return shippingCost.toString();
    }
}
