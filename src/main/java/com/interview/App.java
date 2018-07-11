package com.interview;


import com.interview.util.Utils;
import com.interview.util.XMLConverter;
import com.interview.parsers.OfferParser;
import com.interview.service.ServiceParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class App {
    private static final Logger logger = LogManager.getLogger(OfferParser.class);

    public static void main(String[] args) {
        logger.info("Starting");

        long startTime = System.currentTimeMillis();
        ServiceParser serviceParser;

        if (args.length != 0) {

            serviceParser = new ServiceParser(args[0]);
            serviceParser.go();
        }

        while (!Utils.getThreadsPool().isEmpty()) {}
//        OutputManager.write(OffersList.getOffers(), "d:\\result.xml");
        XMLConverter.convert();

        long exTime = (System.currentTimeMillis() - startTime) / 1000;
        logger.info(String.format("Execution time: %d", exTime));
    }
}
