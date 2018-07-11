package com.interview;


import com.interview.util.SetsHolder;
import com.interview.util.SummaryPrinter;
import com.interview.util.Utils;
import com.interview.util.XMLWriter;
import com.interview.parsers.OfferParser;
import com.interview.service.ServiceParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This app receives a keyword as first argument in the main method
 * and starts searching related offers on the site "https://www.aboutyou.de".
 * Found parsed offers storing in the /target/offers.xml
 * App can try to cheat server bot detection by sleeping each thread with random sleep time.
 */
public class App {
    private static final Logger logger = LogManager.getLogger(OfferParser.class);

    public static void main(String[] args) {
        logger.debug("Starting");

        SummaryPrinter.startCollecting();


        if (args.length != 0) {
            Utils.setCheating(true);
            ServiceParser serviceParser = new ServiceParser(args[0]);
            serviceParser.go();
        }

        // Waiting for the ending of execution of all threads
        while (!SetsHolder.THREADS_POOL.isEmpty()) {}

        XMLWriter.writeResults();

        SummaryPrinter.printSummary();

    }
}
