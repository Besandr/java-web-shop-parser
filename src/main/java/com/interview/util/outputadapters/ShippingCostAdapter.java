package com.interview.util.outputadapters;

import com.interview.model.properties.ShippingCost;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ShippingCostAdapter extends XmlAdapter<String, ShippingCost> {

    public String marshal(ShippingCost shippingCost) throws Exception {
        return shippingCost.toString();
    }

    public ShippingCost unmarshal(String currency) throws Exception {
        return null;
    }

}
