package com.besandr.util.outputadapters;

import com.besandr.model.properties.Name;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class NameAdapter extends XmlAdapter<String, Name> {

    public String marshal(Name name) throws Exception {
        return name.toString();
    }

    public Name unmarshal(String name) throws Exception {
        return null;
    }

}
