package com.besandr.model.properties;

public class Currency implements Property {

    private String currency;

    public Currency (String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return currency;
    }

    @Override
    public String getName() {
        return "currency";
    }
}
