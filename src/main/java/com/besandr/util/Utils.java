package com.besandr.util;


import java.math.BigDecimal;


public class Utils {

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

    public static String stringFromStringsArray(String[] arr) {
        StringBuilder result = new StringBuilder();
        for (String s : arr) {
            result.append(s).append(" ");
        }
        return result.toString();
    }

    public static void printProgressBar(){
        System.out.print(String.format("\rParsing in process. [%d] offers have already been parsed", SetsHolder.offersCounter.get()));
    }
}
