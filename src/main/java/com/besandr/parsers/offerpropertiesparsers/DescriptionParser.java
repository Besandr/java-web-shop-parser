package com.besandr.parsers.offerpropertiesparsers;

import com.besandr.model.properties.Description;
import com.besandr.model.properties.Property;
import com.besandr.util.Const;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DescriptionParser implements PropertyParser {

    @Override
    public Property parse(Document offerPage) {

        String description = null;
        Elements descr = offerPage.getElementsByAttributeValueContaining("class", Const.DESCRIPTION_KEY);
        if (descr.size() != 0) {
            //Receiving the text of description and removing the redundant ArticleID from it
            description = descr.text().
                    replace(Const.ARTICLE_KEYWORD + new ArticleIDParser().parse(offerPage).toString(), "").
                    trim();
        }
        return new Description(description);
    }
}
