package com.besandr.service.options;

public enum ConcurrencyOption implements Option {
    ONE_THREAD,
    MULTITHREAD,
    DEFAULT;


    public ConcurrencyOption getDefault(){
        return MULTITHREAD;
    }
}
