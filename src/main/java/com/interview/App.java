package com.interview;


import com.interview.service.AppSettings;
import com.interview.util.SetsHolder;
import com.interview.util.SummaryPrinter;
import com.interview.util.XMLWriter;
import com.interview.parsers.OfferParser;
import com.interview.service.ServiceParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This app receives a keyword as first argument in the main method
 * and starts searching related offers on the site "https://www.aboutyou.de".
 * Found parsed offers after parsing all offer pages stored in the /target/offers.xml
 * App can try to cheat server bot detection by delaying the start of each thread with
 * random sleep time. Sleep time range sets in Utils.sleepTime() method. It also uses
 * only that type of requests to server which can be used by buyer.
 * By default app starts in multi-thread mode with start delay in a 500-1200 millis range
 */
public class App {
    private static final Logger logger = LogManager.getLogger(OfferParser.class);

    public static void main(String[] args) {
        logger.debug("Starting");

        SummaryPrinter.startCollecting();

        // Available concurrency parameters are MULTI_THREAD and ONE_THREAD
        // Available requesting mode parameters are LAZY(with delay on Thread start) and BOOST (without any delay
        AppSettings.concurrency = AppSettings.CONC_MODE.MULTI_THREAD;
        AppSettings.requesting_mode = AppSettings.REQ_MODE.LAZY;



        if (args.length != 0) {
            ServiceParser serviceParser = new ServiceParser(args[0]);
            serviceParser.go();
        }

        // Waiting for the ending of execution of all threads
        while (!SetsHolder.THREADS_POOL.isEmpty()) {}

        XMLWriter.writeResults();

        SummaryPrinter.printSummary();

    }
}
