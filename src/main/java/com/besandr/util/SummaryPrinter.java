package com.besandr.util;

import com.besandr.parsers.OfferParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class SummaryPrinter {
    private static final Logger logger = LogManager.getLogger(OfferParser.class);

    // Program makes at least one http search request
    private static int httpSearchRequestAmount = 1;

    private static long startTime;

    public static void printSummary() {

        long exTime = (System.currentTimeMillis() - startTime) / 1000;

        addHttpRequestAmount(SetsHolder.OFFER_LINKS_SET.size());
        logger.info(String.format("Amount of triggered HTTP request: %d", httpSearchRequestAmount));

        logger.info(String.format("Execution time: %d seconds", exTime));
        logger.info(String.format("Amount of extracted products: %d", SetsHolder.offersCounter.get()));

    }

    public static void startCollecting() {
        startTime = System.currentTimeMillis();
        MemoryUsage.startControl();
    }

    public static void addHttpRequestAmount(int amount) {
        httpSearchRequestAmount += amount;
    }
}
