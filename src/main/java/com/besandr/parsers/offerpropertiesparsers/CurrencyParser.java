package com.besandr.parsers.offerpropertiesparsers;

import com.besandr.model.properties.Currency;
import com.besandr.model.properties.Property;
import com.besandr.util.Const;
import com.besandr.util.Utils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**Currency parser gets the currency from the same tag as a price parser.
 * It finds a tag which class attribute contains the fragment stored in Const.PRICE_KEY.
 * After that parser take a text of this tag which contains the price itself and the currency type.
 * Utils.currencyDefiner method extracts the currency type from this text
 */


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
