package com.interview.parsers;

import com.interview.util.Const;
import com.interview.util.SetsHolder;
import com.interview.util.SummaryPrinter;
import com.interview.util.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SearchResultParser receives search request URL and parses it
 * for offer links. Links fills the List of offer pages.
 * Program iterates through the List and starts a thread
 * for parsing each one.
 * Also SearchResultParser looking for amount of searching
 * result pages. If there are more than one page it runs iteration
 * which starts new thread (SearchResultParser with second param - false)
 * to parse each result page.
 */


public class SearchResultParser extends AbstractParser {
    private static final Logger logger = LogManager.getLogger(OfferParser.class);

    private Document resultsPage;
    private boolean isThisFirstPage;
    private List<String> offerPages = new ArrayList<>();

    public SearchResultParser(String searchRequest, boolean isThisFirstPage) {
        try{
            resultsPage = Jsoup.connect(searchRequest).get();
            this.isThisFirstPage = isThisFirstPage;
        } catch (IOException e) {
            logger.error(e.getMessage() + e.getStackTrace());
        }
    }

    @Override
    public void run() {
        threadSleep();
        if (isThisFirstPage) {
            startParsingOtherPages();
        }
        parseCurrentPageForOfferLinks();
        if (!offerPages.isEmpty()) {
            offerParseExecutor();
        }
        SetsHolder.THREADS_POOL.remove(this);
    }

    private void parseCurrentPageForOfferLinks(){
        Elements linkTags = resultsPage.select("a[href]");

        for (Element linkTag : linkTags) {
            String link = linkTag.attr("href");
            //Searching for product URL-s. All of them contains /p/
            if (link.contains("/p/")) {
                String offerURL = Const.SITE_URL + link;
                offerPages.add(offerURL);
            }
        }
    }

    private void offerParseExecutor() {
        for (String offerPageURL : offerPages) {
            if (SetsHolder.OFFER_LINKS_SET.add(offerPageURL)) {
                Thread offerParser = new OfferParser(offerPageURL, true);
                SetsHolder.THREADS_POOL.add(offerParser);
                offerParser.start();
            }
        }
    }

    private void startParsingOtherPages() {
       Elements elements = resultsPage.getElementsByAttributeValueContaining("class", Const.PAGE_AMOUNT_KEY);
       // If there are more than one page, after this parse the last element in elements set will contain link to the last page
       // Also tag with that link will contain text - the number of last page
       if (elements.size() > 1) {
           Element lastElement = elements.get(elements.size() - 1);
           Elements lastElementLinks = lastElement.getElementsByAttribute("href");
           int totalPages = Integer.parseInt(lastElementLinks.get(0).text());
           SummaryPrinter.setHttpSearchRequestAmount(totalPages);
           for (int i = 2; i <= totalPages; i++) {
               String nextPageURL = resultsPage.baseUri() + Const.PAGINATION_URL_PARAM + i;
               SearchResultParser nextPageParser = new SearchResultParser(nextPageURL, false);
               nextPageParser.start();
           }
       }
    }
}
