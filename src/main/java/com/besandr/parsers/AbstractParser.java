package com.besandr.parsers;

import com.besandr.service.AppSettings;
import com.besandr.service.options.BotDetectionCheatingOption;
import com.besandr.service.options.ConcurrencyOption;
import com.besandr.util.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractParser extends Thread {
    private static final Logger logger = LogManager.getLogger(OfferParser.class);

    protected void startDelay() {
        if (AppSettings.getBotDetectionCheating() == BotDetectionCheatingOption.ON) {
            try {
                logger.debug("Waiting ....");
                Thread.sleep(Utils.sleepTime());
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }

    protected void threadJoin() {
        if (AppSettings.getConcurrency() == ConcurrencyOption.ONE_THREAD) {
            try {
                super.join();
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
