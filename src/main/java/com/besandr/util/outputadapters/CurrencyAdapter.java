package com.besandr.util.outputadapters;

import com.besandr.model.properties.Currency;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CurrencyAdapter extends XmlAdapter<String, Currency> {

    public String marshal(Currency currency) throws Exception {
        return currency.toString();
    }

    public Currency unmarshal(String currency) throws Exception {
        return null;
    }

}