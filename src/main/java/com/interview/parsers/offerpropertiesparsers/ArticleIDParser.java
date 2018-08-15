package com.interview.parsers.offerpropertiesparsers;

import com.interview.model.properties.ArticleID;
import com.interview.model.properties.Property;
import com.interview.util.Const;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ArticleIDParser implements PropertyParser {

    @Override
    public Property parse(Document offerPage) {
        String articleID = null;
        // Target tag is unique and it has a text field filled with such pattern: "Artikel-Nr: offerArticleID"
        // But some offers doesn't has an article, so we make a check on it
        Elements articleElement = offerPage.getElementsContainingOwnText(Const.ARTICLE_KEYWORD);
        if  (!articleElement.isEmpty()) {
            articleID = articleElement.get(0).text().split(":")[1].trim();
        }
        return new ArticleID(articleID);
    }
}
