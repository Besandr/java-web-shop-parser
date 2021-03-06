package com.besandr;

import com.besandr.util.SummaryPrinter;
import com.besandr.service.ServiceParser;
import com.besandr.util.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This app receives a keywords as first argument in the main method
 * and starts searching related offers on the site "https://www.aboutyou.de".
 * The app parses founded offers and stores it in the /current directory/offers.xml
 *
 * App can try to cheat server bot detection by delaying the start of each thread with
 * random sleep time. Sleep time range sets in Utils.sleepTime() method. It also uses
 * only that type of HTTP requests to server which can be used by user.
 * By default app starts in multi-thread mode with start delay in a 500-1200 millis range
 * This settings can be changed by editing the Settings.ini file
 */

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        Utils.printProgressBar();

        SummaryPrinter.startCollecting();

        if (args.length != 0) {
            ServiceParser serviceParser = new ServiceParser(args);
            serviceParser.go();
        }

        SummaryPrinter.printSummary();

    }
}
