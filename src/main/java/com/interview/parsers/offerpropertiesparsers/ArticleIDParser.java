package com.interview.parsers.offerpropertiesparsers;

import com.interview.model.properties.ArticleID;
import com.interview.model.properties.Property;
import com.interview.util.Const;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ArticleIDParser implements PropertyParser {

    @Override
    public Property parse(Document offerPage) {
        String articleID;
        // Target tag is unique and it has a text field filled with such pattern: "Artikel-Nr: offerArticleID"
        Elements articleElement = offerPage.getElementsContainingOwnText(Const.ARTICLE_KEYWORD);
        articleID = articleElement.get(0).text().split(":")[1].trim();
        return new ArticleID(articleID);
    }
}
