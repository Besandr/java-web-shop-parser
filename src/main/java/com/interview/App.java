package com.interview;

import com.interview.Util.OutputManager;
import com.interview.Util.Utils;
import com.interview.model.OffersList;
import com.interview.service.ServiceParser;


public class App {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ServiceParser serviceParser;

        if (args.length != 0){

            serviceParser = new ServiceParser(args[0]);
            serviceParser.run();
//            Thread parseExecutor = new Thread(serviceParser);
//            parseExecutor.start();
            // Waiting on finishing parsing for calculating execution time
            // start results saving and other
//            try {
//                parseExecutor.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

        while (!Utils.getthreadsPool().isEmpty()) {}
        OutputManager.write(OffersList.getOffers(), "d:\\result.xml");

        long exTime = (System.currentTimeMillis() - startTime) / 1000;
        System.out.println("Execution time: " + exTime);
    }
}
