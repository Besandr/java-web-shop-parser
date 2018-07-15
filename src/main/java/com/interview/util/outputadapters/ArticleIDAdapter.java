package com.interview.util.outputadapters;

import com.interview.model.properties.ArticleID;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ArticleIDAdapter extends XmlAdapter<String, ArticleID> {

    public String marshal(ArticleID articleID) throws Exception {
        return articleID.toString();
    }

    public ArticleID unmarshal(String currency) throws Exception {
        return null;
    }

}