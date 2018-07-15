package com.interview.util.outputadapters;

import com.interview.model.properties.Color;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ColorAdapter extends XmlAdapter<String, Color> {

    public String marshal(Color color) throws Exception {
        return color.toString();
    }

    public Color unmarshal(String currency) throws Exception {
        return null;
    }

}

