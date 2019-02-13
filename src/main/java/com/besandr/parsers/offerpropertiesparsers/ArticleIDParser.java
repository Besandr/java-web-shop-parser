package com.besandr.parsers.offerpropertiesparsers;

import com.besandr.model.properties.ArticleID;
import com.besandr.model.properties.Property;
import com.besandr.util.Const;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**ArticleID parser finds a tag with specific text. Target tag is unique and it
 * has a text field filled with such pattern: "Artikel-Nr: offerArticleID" *
 */
public class ArticleIDParser implements PropertyParser {

    @Override
    public Property parse(Document offerPage) {
        String articleID = null;
        // Some offers doesn't has an article, so we make a check on it
        Elements articleElement = offerPage.getElementsContainingOwnText(Const.ARTICLE_KEYWORD);
        if  (!articleElement.isEmpty()) {
            String articleText = articleElement.get(0).text();
            articleID = articleText.substring(articleText.indexOf(":") + 1).trim();
        }
        return new ArticleID(articleID);
    }
}
