package com.besandr.util;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SetsHolder {

    public static final Set<String> OFFER_LINKS_SET = ConcurrentHashMap.newKeySet();

    public static final Set<Thread> THREADS_POOL = ConcurrentHashMap.newKeySet();

}