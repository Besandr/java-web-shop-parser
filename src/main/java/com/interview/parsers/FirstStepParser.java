package com.interview.parsers;

import com.interview.Util.Const;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FirstStepParser receives search request URL and parses it
 * for offer links. Links fills the List of offer pages.
 * Program iterates through the List and starts a thread
 * for parsing each one.
 * Also FirstStepParser looking for number of searching
 * result pages. If there are more than one page it runs iteration
 * which starts new thread (FirstStepParser with second param - false)
 * to parse each result page.
 */


public class FirstStepParser extends AbstrParser {

    Document resultsPage;
    private boolean isThisFirstPage;
    List<String> offerPages = new ArrayList<>();

    public FirstStepParser(String searchRequest, boolean isThisFirstPage) {
        try{
            resultsPage = Jsoup.connect(searchRequest).get();
            this.isThisFirstPage = isThisFirstPage;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
        System.out.println("\nParsing  " + resultsPage.baseUri() + " by  " + this.getClass().getSimpleName());
        if (isThisFirstPage) {
            startParsingOtherPages();
        }
        parseCurrentPageForOfferLinks();
    }

    private void parseCurrentPageForOfferLinks(){
        Elements linkTags = resultsPage.select("a[href]");

        for (Element linkTag : linkTags) {
            String link = linkTag.attr("href");
            //Searching for product links. All of them contains /p/
            if (link.contains("/p/")) {
                String linkForSecondStep = Const.SITE_URL + link;
                offerPages.add(linkForSecondStep);
            }
        }
        if (offerPages.size() != 0) {
            secondStepExecutor();
        }
    }

    private void secondStepExecutor() {
        for (String offerPage : offerPages) {
            try {
                Document mainOfferPage = Jsoup.connect(offerPage).get();
                Thread secondStep = new SecondStepParser(mainOfferPage);
                secondStep.start();

                ((SecondStepParser) secondStep).threadJoin();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void startParsingOtherPages() {
       Elements elements = resultsPage.getElementsByAttributeValueContaining("class", Const.PAGE_AMOUNT_KEY);
       // If there are more than one page, after this parse last element in elements will contain link to the last page
       // And tag with that link will contain text - the number of last page
       if (elements.size() > 1) {
           Element lastElement = elements.get(elements.size() - 1);
           Elements lastElementLinks = lastElement.getElementsByAttribute("href");
           int totalPages = Integer.parseInt(lastElementLinks.get(0).text());
           for (int i = 2; i <= totalPages; i++) {
               String nextPageURL = resultsPage.baseUri() + Const.PAGINATION_URL_PARAM + i;
               FirstStepParser nextPageParser = new FirstStepParser(nextPageURL, false);
               nextPageParser.start();
           }
       }
    }
}
