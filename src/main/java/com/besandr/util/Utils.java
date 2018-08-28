package com.besandr.util;


import java.math.BigDecimal;


public class Utils {

//    @Setter
//    public static boolean cheating;

    public static int sleepTime(){
        return (int) (Math.random() * 1200) + 500;
    }

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
