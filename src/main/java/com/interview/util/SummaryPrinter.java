package com.interview.util;

import com.interview.model.OffersList;
import com.interview.parsers.OfferParser;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.NumberFormat;


public class SummaryPrinter {
    private static final Logger logger = LogManager.getLogger(OfferParser.class);


    @Setter private static int httpSearchRequestAmount;
    private static long startTime;
    private static NumberFormat format = NumberFormat.getInstance();

    public static void printSummary() {
        // Program makes at least one http search request
        if (httpSearchRequestAmount == 0) {
            httpSearchRequestAmount = 1;
        }
        long exTime = (System.currentTimeMillis() - startTime) / 1000;

        logger.info(String.format("Amount of triggered HTTP request: %d", httpSearchRequestAmount + SetsHolder.OFFER_LINKS_SET.size() ));
        logger.info(String.format("Execution time: %d seconds", exTime));
        logger.info(String.format("Max memory usage: %s kilobytes", format.format(MemoryUsage.getMaxMemory() / 1024)));
        logger.info(String.format("Amount of extracted products: %d", OffersList.getOffers().size()));

    }

    public static void startCollecting() {
        startTime = System.currentTimeMillis();
        MemoryUsage.startControl();
    }
}
