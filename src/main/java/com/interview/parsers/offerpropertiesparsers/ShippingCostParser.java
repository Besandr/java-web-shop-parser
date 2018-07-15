package com.interview.parsers.offerpropertiesparsers;

import com.interview.model.properties.Property;
import com.interview.model.properties.ShippingCost;
import com.interview.util.Const;
import com.interview.util.Utils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.math.BigDecimal;

public class ShippingCostParser implements PropertyParser {

    @Override
    public Property parse(Document offerPage){

        BigDecimal shippingCost;
        Elements shipCostElement = offerPage.getElementsByClass(Const.SHIPPING_COSTS_CLASS);
        shippingCost = Utils.getCostFromString(shipCostElement.text());

        return new ShippingCost(shippingCost);
    }
}
