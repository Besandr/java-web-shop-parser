package com.interview.service;

import com.interview.util.Const;
import com.interview.util.Utils;
import com.interview.parsers.SearchResultParser;

/**
 * ServiceParser receive keyword, and generates three search request URLs,
 * one for each search category.
 * After that it starts three threads for parsing it.
 */

public class ServiceParser{

    private String keyword;

    public ServiceParser(String keyword) {
        this.keyword = keyword;
    }

    public void go() {
        for (String category : Const.CATEGORIES) {
            String searchRequest = Const.SITE_URL + Const.SEARCH_PARAM + keyword + Const.CATEGORY_PARAM + category;
            Thread searchResultParser = new SearchResultParser(searchRequest, true);
            Utils.getThreadsPool().add(searchResultParser);
            searchResultParser.start();
        }

    }




}
