package com.interview.util;

import java.math.BigDecimal;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Utils {




    public static int sleepTime(){
        return (int) (Math.random() * 5000) + 2000;
    }
//    public static synchronized Set getOfferLinksSet() {
//        return OFFER_LINKS_SET;
//    }
//
//    public static synchronized Set getThreadsPool() {
//        return THREADS_POOL;
//    }

    public static String currencyDefiner(String textWithCurrencySign) {
        String currency = null;
        if (textWithCurrencySign.contains("€")) {
            currency = "EUR";
        }
        if (textWithCurrencySign.contains("CHF")) {
            currency = "CHF";
        }
        return currency;
    }

    public static BigDecimal getCostFromString(String cost) {
        return new BigDecimal(cost.replaceAll("[^0-9,]*", "")
                .replace("," , "."));
    }
}
