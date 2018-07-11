package com.interview.parsers;

import com.interview.Util.Utils;

public abstract class AbstrParser extends Thread {
    @Override
    public void run() {
        try {
            System.out.printf("Waiting ....");
            Thread.sleep(Utils.sleepTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void threadJoin(){
        try {
            super.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
