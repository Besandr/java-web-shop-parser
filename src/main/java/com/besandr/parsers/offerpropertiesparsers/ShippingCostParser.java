package com.besandr.parsers.offerpropertiesparsers;

import com.besandr.model.properties.Property;
import com.besandr.model.properties.ShippingCost;
import com.besandr.util.Const;
import com.besandr.util.Utils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.math.BigDecimal;

/**Shipping cost parser finds a target tag by a shipping cost keyword stored in Const.SHIPPING_COSTS_KEYWORD
 * After that it extracts the cost from its text with Utils.getCostFromString method
 */

public class ShippingCostParser implements PropertyParser {

    @Override
    public Property parse(Document offerPage){

        BigDecimal shippingCost = null;
        Elements shipCostElement = offerPage.getElementsContainingOwnText(Const.SHIPPING_COSTS_KEYWORD);
        if (!shipCostElement.isEmpty()) {
            shippingCost = Utils.getCostFromString(shipCostElement.get(0).text());
        }


        return new ShippingCost(shippingCost);
    }
}
