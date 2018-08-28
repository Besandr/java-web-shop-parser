package com.besandr.service;

import com.besandr.util.Const;
import com.besandr.util.SetsHolder;
import com.besandr.parsers.SearchResultParser;

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
            SetsHolder.THREADS_POOL.add(searchResultParser);
            searchResultParser.start();
        }
    }
}
