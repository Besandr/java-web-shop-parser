package com.interview.parsers;

import com.interview.util.Utils;

public abstract class AbstractParser extends Thread {
    @Override
    public void run() {
        threadSleep();
    }


    public void threadJoin(){
        try {
            super.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void threadSleep() {
        try {
            System.out.printf("Waiting ....");
            Thread.sleep(Utils.sleepTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
