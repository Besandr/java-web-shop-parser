package com.besandr.util.outputadapters;

import com.besandr.model.properties.Description;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DescriptionAdapter extends XmlAdapter<String, Description> {

    public String marshal(Description description) throws Exception {
        return description.toString();
    }

    public Description unmarshal(String currency) throws Exception {
        return null;
    }

}