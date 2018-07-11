package com.interview.service;

import com.interview.Util.Const;
import com.interview.parsers.SearchResultParser;

/**
 * ServiceParser receive keyword, and generates three search request URLs,
 * one for each search category.
 * After that it starts three threads for parsing it.
 */

public class ServiceParser implements Runnable {

    private String keyword;

    public ServiceParser(String keyword) {
        this.keyword = keyword;
    }

    public void run() {
        for (String category : Const.CATEGORIES) {
            String searchRequest = Const.SITE_URL + Const.SEARCH_PARAM + keyword + Const.CATEGORY_PARAM + category;
            Thread firstStepThread = new SearchResultParser(searchRequest, true);
            firstStepThread.start();
        }
    }




}
