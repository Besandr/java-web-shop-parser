package com.interview.parsers.offerpropertiesparsers;

import com.interview.model.properties.InitialPrice;
import com.interview.model.properties.Property;
import com.interview.util.Const;
import com.interview.util.Utils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.math.BigDecimal;

public class InitialPriceParser implements PropertyParser {

    @Override
    public Property parse(Document offerPage){
        BigDecimal initialPrice = null;
        // Some offers doesn't have an initial price, so default return value is null
        // But if it has, the div tag will contain text string with initial price
        // And sometimes shop provides sales and writes several initial prices
        // Parser iterates through them and chooses the maximal price
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
