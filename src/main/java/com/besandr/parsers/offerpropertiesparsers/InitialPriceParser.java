package com.besandr.parsers.offerpropertiesparsers;

import com.besandr.model.properties.InitialPrice;
import com.besandr.model.properties.Property;
import com.besandr.util.Const;
import com.besandr.util.Utils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.math.BigDecimal;

/**Initial price parser finds a tag which class attribute contains the fragment stored in Const.INITIAL_PRICE_KEY.
 * Some offers doesn't have an initial price, so default return value is null
 * But if it has, the initial price tag will contain text string with initial price
 * And sometimes shop provides sales and writes several initial prices
 * Parser iterates through them and chooses the maximal price
 */

public class InitialPriceParser implements PropertyParser {

    @Override
    public Property parse(Document offerPage){
        BigDecimal initialPrice = null;
        Elements initialPriceDiv = offerPage.getElementsByAttributeValueContaining("class", Const.INITIAL_PRICE_KEY);
        if (initialPriceDiv.size() != 0) {
            BigDecimal maxInitPrice = BigDecimal.ZERO;
            for (int i = 0; i < initialPriceDiv.size(); i++) {
                maxInitPrice = maxInitPrice.max(Utils.getCostFromString(initialPriceDiv.get(i).text()));
            }
            initialPrice = maxInitPrice;
        }

        return new InitialPrice(initialPrice);

    }
}
