package com.besandr.parsers.offerpropertiesparsers;

import com.besandr.model.properties.Property;
import com.besandr.model.properties.ShippingCost;
import com.besandr.util.Const;
import com.besandr.util.Utils;
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
