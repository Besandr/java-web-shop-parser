package com.interview.util;



import java.text.NumberFormat;

public class MemoryUsage {
    private static Runtime runtime;
    private static NumberFormat format;
    private static long maxMemory;


    public static void startControl(){
        runtime = Runtime.getRuntime();

    }

    public static long getMaxMemory() {
        return runtime.maxMemory();
    }

    public static void printMemory() {


        NumberFormat format = NumberFormat.getInstance();

        StringBuilder sb = new StringBuilder();

        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();

        sb.append("free memory: " + format.format(freeMemory / 1024) + "<br/>");
        sb.append("allocated memory: " + format.format(allocatedMemory / 1024) + "<br/>");
        sb.append("max memory: " + format.format(maxMemory / 1024) + "<br/>");
        sb.append("total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) + "<br/>");
        System.out.println(sb);
    }
}
