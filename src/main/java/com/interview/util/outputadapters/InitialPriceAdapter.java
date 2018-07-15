package com.interview.util.outputadapters;

import com.interview.model.properties.InitialPrice;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class InitialPriceAdapter extends XmlAdapter<String, InitialPrice> {

    public String marshal(InitialPrice initialPrice) throws Exception {
        if (initialPrice.getInitialPrice() != null) {
            return initialPrice.toString();
        } else {
            return null;
        }
    }

    public InitialPrice unmarshal (String initialPrice) throws Exception {
        return null;
    }

}
