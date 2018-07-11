package com.interview.parsers;

import com.interview.Util.Const;
import com.interview.Util.Utils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SecondStepParser extends AbstrParser {

    Document mainOfferPage;

    public SecondStepParser(Document mainOfferPage) {
        this.mainOfferPage = mainOfferPage;
    }

    @Override
    public void run() {
        System.out.println("\nParsing  " + mainOfferPage.baseUri() + " by  " + this.getClass().getSimpleName());

        super.run();

        Elements colorElements = mainOfferPage.getElementsByClass(Const.COLOR_CLASS_KEY);
        for (Element colorClass : colorElements) {
            Elements color = colorClass.select("a[href]");
            String offerLink = color.get(0).attr("href"); // Target link is always first element in Elements color
            if (Utils.getOfferLinksSet().add(offerLink)) {
                String offerURL = Const.SITE_URL + offerLink;
////                new Thread(new OfferParser(offerURL)).start();
//                Thread thread = new Thread(new OfferParser(offerURL));
                Thread offerParseThread = new OfferParser(offerURL);
                offerParseThread.start();
                try {
                    offerParseThread.join();
                } catch (InterruptedException e) {


                }
            }
        }
    }
}
