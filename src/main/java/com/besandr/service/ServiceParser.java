package com.besandr.service;

import com.besandr.util.Const;
import com.besandr.util.SetsHolder;
import com.besandr.parsers.SearchResultParser;
import com.besandr.util.Utils;
import com.besandr.util.resultwriters.XMLWriter;

/**
 * ServiceParser receive keyword, and generates three search request URLs,
 * one for each search category.
 * After that it starts three threads for parsing it.
 */

public class ServiceParser{

    private String keywords;

    public ServiceParser(String[] keywords) {
        this.keywords = Utils.stringFromStringsArray(keywords);
    }

    public void go() {

        XMLWriter.writeHeaderElement();

        for (String category : Const.CATEGORIES) {
            String searchRequest = Const.SITE_URL + Const.SEARCH_PARAM + keywords + Const.CATEGORY_PARAM + category;
            Thread searchResultParser = new SearchResultParser(searchRequest, true);
            SetsHolder.THREADS_POOL.add(searchResultParser);
            searchResultParser.start();
        }
    }
}
