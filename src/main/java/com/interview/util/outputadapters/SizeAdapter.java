package com.interview.util.outputadapters;


import com.interview.model.properties.Size;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class SizeAdapter extends XmlAdapter<String, Size> {

    public String marshal(Size size) throws Exception {
        return size.toString();
    }

    public Size unmarshal(String brand) throws Exception {
        return null;
    }

}
