package com.interview.util.outputadapters;

import com.interview.model.properties.Price;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class PriceAdapter extends XmlAdapter<String, Price> {

    public String marshal(Price price) throws Exception {
        return price.toString();
    }

    public Price unmarshal(String price) throws Exception {
        return null;
    }
}
