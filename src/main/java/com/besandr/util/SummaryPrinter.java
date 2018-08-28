package com.besandr.util;

import com.besandr.model.OffersList;
import com.besandr.parsers.OfferParser;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.NumberFormat;


public class SummaryPrinter {
    private static final Logger logger = LogManager.getLogger(OfferParser.class);

    // Program makes at least one http search request
    private static int httpSearchRequestAmount = 1;

    private static long startTime;
    private static NumberFormat format = NumberFormat.getInstance();

    public static void printSummary() {

        long exTime = (System.currentTimeMillis() - startTime) / 1000;

        addHttpRequestAmoutn(SetsHolder.OFFER_LINKS_SET.size());
        logger.info(String.format("Amount of triggered HTTP request: %d", httpSearchRequestAmount));

        logger.info(String.format("Execution time: %d seconds", exTime));
        logger.info(String.format("Max memory usage: %s kilobytes", format.format(MemoryUsage.getMaxMemory() / 1024)));
        logger.info(String.format("Amount of extracted products: %d", OffersList.getInstance().getOffers()));

    }

    public static void startCollecting() {
        startTime = System.currentTimeMillis();
        MemoryUsage.startControl();
    }

    public static void addHttpRequestAmoutn(int amount) {
        httpSearchRequestAmount += amount;
    }
}
