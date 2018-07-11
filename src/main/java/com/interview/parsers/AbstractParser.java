package com.interview.parsers;

import com.interview.util.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractParser extends Thread {
    private static final Logger logger = LogManager.getLogger(OfferParser.class);

    protected void threadSleep() {
        if (Utils.cheating) {
            try {
                logger.debug("Waiting ....");
                Thread.sleep(Utils.sleepTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
