package com.besandr.parsers.offerpropertiesparsers;

import com.besandr.model.properties.Price;
import com.besandr.model.properties.Property;
import com.besandr.util.Const;
import com.besandr.util.Utils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**Price parser finds a tag which class attribute contains the fragment stored in Const.PRICE_KEY.
 * After that parser take a text of this tag and extracts from it the price itself by Utils.getCostFromString method.
 */
public class PriceParser implements PropertyParser {



    @Override
    public Property parse(Document offerPage){

        Elements priceDiv = offerPage.getElementsByAttributeValueContaining("class", Const.PRICE_KEY);
        String priceText = priceDiv.text();

        return new Price(Utils.getCostFromString(priceText));
    }
}
