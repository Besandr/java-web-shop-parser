package com.besandr.util.outputadapters;

import com.besandr.model.properties.InitialPrice;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class InitialPriceAdapter extends XmlAdapter<String, InitialPrice> {

    public String marshal(InitialPrice initialPrice) throws Exception {
        if (initialPrice != null) {
            return initialPrice.toString();
        } else {
            return null;
        }
    }

    public InitialPrice unmarshal (String initialPrice) throws Exception {
        return null;
    }

}
