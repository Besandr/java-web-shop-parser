package com.interview.parsers;

import com.interview.service.AppSettings;
import com.interview.util.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractParser extends Thread {
    private static final Logger logger = LogManager.getLogger(OfferParser.class);

    protected void startDelay() {
        if (AppSettings.requesting_mode == AppSettings.REQ_MODE.LAZY) {
            try {
                logger.debug("Waiting ....");
                Thread.sleep(Utils.sleepTime());
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }

    protected void threadJoin() {
        if (AppSettings.concurrency == AppSettings.CONC_MODE.ONE_THREAD) {
            try {
                super.join();
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
