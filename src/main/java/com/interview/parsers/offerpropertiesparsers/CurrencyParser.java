package com.interview.parsers.offerpropertiesparsers;

import com.interview.model.properties.Currency;
import com.interview.model.properties.Property;
import com.interview.util.Const;
import com.interview.util.Utils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CurrencyParser implements PropertyParser {

    @Override
    public Property parse(Document offerPage) {

        Elements priceDiv = offerPage.getElementsByAttributeValueContaining("class", Const.PRICE_KEY);
        // Target price tag is always first (and single) element in Elements nameClass
        // priceText string pattern is "ab "(optionally) with wanted price and currency type.
        // Parsing for price string and passing it to the Util method for defining price currency
        String priceText = priceDiv.get(0).text();

        return new Currency(Utils.currencyDefiner(priceText));
    }
}
