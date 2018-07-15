package com.interview.util.outputadapters;

import com.interview.model.properties.Brand;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BrandAdapter extends XmlAdapter<String, Brand> {

    public String marshal(Brand brand) throws Exception {
        return brand.toString();
    }

    public Brand unmarshal(String brand) throws Exception {
        return null;
    }

}
