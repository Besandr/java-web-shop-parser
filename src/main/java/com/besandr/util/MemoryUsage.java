package com.besandr.util;


public class MemoryUsage {
    private static Runtime runtime;


    public static void startControl(){
        runtime = Runtime.getRuntime();

    }

    public static long getMaxMemory() {
        return runtime.maxMemory();
    }

}
