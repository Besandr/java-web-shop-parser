package com.interview.parsers.offerpropertiesparsers;

import com.interview.model.properties.Price;
import com.interview.model.properties.Property;
import com.interview.util.Const;
import com.interview.util.Utils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class PriceParser implements PropertyParser {


    @Override
    public Property parse(Document offerPage){

        Elements priceDiv = offerPage.getElementsByAttributeValueContaining("class", Const.PRICE_KEY);
        String priceText = priceDiv.text();

        return new Price(Utils.getCostFromString(priceText));
    }
}
