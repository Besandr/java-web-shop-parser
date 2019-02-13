package com.besandr.parsers.offerpropertiesparsers;

import com.besandr.model.properties.Description;
import com.besandr.model.properties.Property;
import com.besandr.util.Const;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**Description parser finds a tag which class attribute contains the fragment stored in Const.DESCRIPTION_KEY.
 * It gets a text with description from this tag and removes the redundant ArticleID from it
 */

public class DescriptionParser implements PropertyParser {

    @Override
    public Property parse(Document offerPage) {

        String description = null;
        Elements descr = offerPage.getElementsByAttributeValueContaining("class", Const.DESCRIPTION_KEY);
        if (descr.size() != 0) {
            description = descr.text().
                    replace(Const.ARTICLE_KEYWORD + new ArticleIDParser().parse(offerPage).toString(), "").
                    trim();
        }
        return new Description(description);
    }
}
