package com.besandr.model.properties;

public class ArticleID implements Property {

    private String articleID;

    public ArticleID(String articleID) {
        this.articleID = articleID;
    }

    @Override
    public String toString() {
        return articleID;
    }

    @Override
    public String getName() {
        return "articleID";
    }
}
