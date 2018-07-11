//package com.interview.parsers;
//
//import com.interview.util.Const;
//import com.interview.util.Utils;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class SecondStepParser extends AbstractParser {
//
//
//    private Document offerPage;
//    private boolean isExtractionRequired;
//    private List<String> offerPagesByColor = new ArrayList<>();
//
//    public SecondStepParser(String offerPageURL, boolean isExtractionRequired) {
//        this.isExtractionRequired = isExtractionRequired;
//        try {
//            this.offerPage = Jsoup.connect(offerPageURL).get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void go() {
//        System.out.println("\nParsing  " + offerPage.baseUri() + " by  " + this.getClass().getSimpleName());
//
////        super.go();
//
////        Elements colorElements = offerPage.getElementsByClass(Const.COLOR_CLASS_KEY);
////        for (Element colorClass : colorElements) {
////            Elements color = colorClass.select("a[href]");
////            String offerLink = color.get(0).attr("href"); // Target color link is always first element in Elements color
////            if (Utils.getOfferLinksSet().add(offerLink)) {
////                String offerURL = Const.SITE_URL + offerLink;
////                Thread offerParseThread = new OfferParser(offerURL);
////                offerParseThread.start();
////                try {
////                    offerParseThread.join();
////                } catch (InterruptedException e) {}
////            }
////        }
//    }
//}
