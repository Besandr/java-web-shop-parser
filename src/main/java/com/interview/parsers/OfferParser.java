package com.interview.parsers;

import com.interview.model.OfferWithSizes;
import com.interview.parsers.offerpropertiesparsers.PropertyParser;
import com.interview.service.AppSettings;
import com.interview.util.Const;
import com.interview.util.SetsHolder;
import com.interview.model.OffersList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OfferParser extends AbstractParser {
    private static final Logger logger = LogManager.getLogger(OfferParser.class);

    private Document offerPage;
    private boolean isExtractionRequired;
    private List<String> restColorPages = new ArrayList<>();
    private String offerURL;


    OfferParser (String offerURL, boolean isExtractionRequired){
        this.isExtractionRequired = isExtractionRequired;
        try {
            this.offerURL = offerURL;
            // Sometimes reading timeout not enough for getting the page.
            // So this loop makes ten tries to connect and get the http page
            // If all tries failed the run method will interrupt current thread
            for (int i = 0; offerPage == null && i < 10; i++) {
                offerPage = Jsoup.connect(offerURL).get();
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
            logger.error("OfferParser constructor didn't receive the http page with URL : " + offerURL);
        }
    }


    @Override
    public void run() {

        startDelay();

        if (offerPage == null) {
            logger.error("Current thread didn't receive the http page with URL :   " + offerURL);
            SetsHolder.THREADS_POOL.remove(this);
            Thread.currentThread().interrupt();
        }

        if (isExtractionRequired) {
            colorPagesFinder();
            restColorsParseStarter();
        }
        // Parsing current offer page
        if (isOfferActive()) {
            createOffer();
        }
        SetsHolder.THREADS_POOL.remove(this);
    }


    private void restColorsParseStarter() {
        if (!restColorPages.isEmpty()) {
//            for (int i = 0; i < restColorPages.size(); i++) {
            for (String anotherColorPage : restColorPages) {
                if (SetsHolder.OFFER_LINKS_SET.add(anotherColorPage)) {
                    OfferParser offerParser = new OfferParser(anotherColorPage, false);
                    SetsHolder.THREADS_POOL.add(offerParser);
                    offerParser.start();
                    offerParser.threadJoin();
                }
            }
        }
    }

    private void colorPagesFinder() {

        Elements colorElements = offerPage.getElementsByClass(Const.COLOR_CLASS_KEY);
        for (Element colorClass : colorElements) {
            Elements color = colorClass.select("a[href]");
            String offerLink = color.get(0).attr("href"); // Target color link is always first element in Elements color
            String offerURL = Const.SITE_URL + offerLink;
            restColorPages.add(offerURL);
        }
    }

    private void createOffer() {
        logger.debug(String.format("\nParsing  + %s", offerPage.baseUri()));

        OfferWithSizes offerWithSizes = new OfferWithSizes(OffersList.getInstance());
        for (PropertyParser parser : AppSettings.propertiesList) {
            offerWithSizes.setProperty(parser.parse(offerPage));
        }

        offerWithSizes.save();
    }

    private boolean isOfferActive() {
        // There are product pages contains a sold out product
        Elements elements = offerPage.getElementsContainingOwnText(Const.SOLD_OUT_PRODUCT_TEXT);
        return elements.isEmpty();
    }


}
