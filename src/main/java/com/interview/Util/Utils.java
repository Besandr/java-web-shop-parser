package com.interview.Util;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Utils {

    private static volatile ConcurrentHashMap mapForSetOfLinks = new ConcurrentHashMap();
    private static volatile Set<String> offerLinksSet = mapForSetOfLinks.newKeySet();


    public static int sleepTime(){
        int millisTime = (int) (Math.random() * 5000) + 2000;
        return millisTime;
    }
    public static synchronized Set getOfferLinksSet() {
        return offerLinksSet;
    }
}