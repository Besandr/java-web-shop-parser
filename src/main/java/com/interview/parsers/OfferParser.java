package com.interview.parsers;

import com.interview.Util.Const;
import com.interview.model.Offer;
import com.interview.model.OffersList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OfferParser extends AbstrParser {

//    File htmlFile = new File("D:\\1.htm");

    private Document offerDoc;
    private Offer offer;


    public OfferParser (String offerURL){
        try {
            offerDoc = Jsoup.connect(offerURL).get();

//            offerDoc = Jsoup.parse(htmlFile, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        System.out.println("\nParsing  " + offerDoc.baseUri() + " by  " + this.getClass().getSimpleName());
        super.run();
        offer = new Offer(OffersList.getInstance());
        offer.setName(parseName());
        offer.setBrand(parseBrand());
        offer.setColor(parseColor());
        offer.setPrice(parsePrice());
        offer.setInitialPrice(parseInitialPrice());
        offer.setArticleID(parseArticleID());
        offer.setShippingCost(parseShippingCost());
        offer.setSizes(parseSizes());
        offer.save();


        //        offer.builder()
//                .savePath(OffersList.getInstance())
//                .name(parseName())
//                .brand(parseBrand())
//                .color(parseColor())
//                .price(parsePrice())
//                .initialPrice(parseInitialPrice())
//                .articleID(parseArticleID())
//                .shippingCost(parseShippingCost())
//                .sizes(parseSizes());
//        System.out.println(offer);
//        offer.save();
    }

//    public static void main(String[] args) {
//        OfferParser parser = new OfferParser(null);
//        parser.run();
//    }


    private String parseName() {
        String name;
        Elements nameClass = offerDoc.getElementsByClass(Const.NAME_CLASS);
        String[] brandAndName = nameClass.get(0).text().split("[|]"); // Target brand&name text is always first element in Elements nameClass
        name = brandAndName[1].trim();                     // brand&name string parrern is: "Produktinfos: offerBrand | offerName"
        return name;
    }

    private String parseBrand() {
        String brand;
        Elements nameClass = offerDoc.getElementsByClass(Const.BRAND_CLASS);
        // Target brand&name text is always first (and single) element in Elements nameClass
        // brand&name string parrern is: "Produktinfos: offerBrand | offerName"
        String[] brandAndName = nameClass.get(0).text().split("[|]");
        int colonIndex = brandAndName[0].indexOf(":");
        brand = brandAndName[0].substring(colonIndex + 1).trim();
        return brand;
    }

    private String parseColor() {
        String color;
        String colorTitleTag = offerDoc.title();
        //Title pattern is "offerBrand offerName in offerColor | ABOUT YOU"
        //There is no split symbols between Brand and Name so parsing for them in title is too risky
        //I parse only color here
        String colorAndSite = colorTitleTag.split(" in ")[1];
        color = colorAndSite.split("[|]")[0].trim();

        return color;
    }

    private String parsePrice() {
        // Price can be parsed from the JSON request on the page, but I want to keep
        // price format presented on the page.

        String price;
        Elements priceDiv = offerDoc.getElementsByAttributeValueContaining("class", Const.PRICE_KEY);
        // Target brand&name text is always first (and single) element in Elements nameClass
        // priceText string parrern is "ab "(optionally) with wanted price
        // After splitting the price always will be the last element in String[] array
        String priceText = priceDiv.get(0).text().trim();
        String[] splittedPriceText = priceText.split(" ");
        price = splittedPriceText[splittedPriceText.length - 1];
        return price;
    }

    private String parseInitialPrice() {
        String initialPrice = null;
        // Some offers doesn't have an initial price, so default return value is null
        // But if it has it, the div tag will contain only text with initial Price
        Elements initialPriceDiv = offerDoc.getElementsByAttributeValueContaining("class", Const.INITIAL_PRICE_KEY);
        if (initialPriceDiv.size() != 0) {
            initialPrice = initialPriceDiv.get(0).text();
        }
        return initialPrice;
    }

    private String parseArticleID() {
        String articleID;
        // Target tag is unique and it has a text field filled with such pattern: "Artikel-Nr: offerArticleID"
        Elements articleElement = offerDoc.getElementsContainingOwnText(Const.ARTICLE_KEYWORD);
        articleID = articleElement.get(0).text().split(":")[1].trim();
        return articleID;
    }

    private String parseShippingCost() {
        String shippingCost;
        Elements shipCostElement = offerDoc.getElementsByClass(Const.SHIPPING_COSTS_CLASS);
        String costString = shipCostElement.get(0).text();
        int beginning = costString.indexOf("+") + 1;
        int end = costString.indexOf("Versand");
        shippingCost = costString.substring(beginning, end).trim();
        return shippingCost;
    }

    private List<String> parseSizes() {

        ArrayList<String> sizes = new ArrayList<>();
        Elements sizeElements = offerDoc.getElementsByAttributeValueContaining("class", Const.SIZE_KEY);
        if (sizeElements.size() != 0) {
            for (Element sizeElement :
                    sizeElements) {
                // Check for sold out sizes
                if (!sizeElement.text().contains(Const.SOLD_SIZE_KEY)) {
                    String[] sizeString = sizeElement.text().trim().split(" ");
                    // Some offers has additional information before size. Receiving only last one
                    String size = sizeString[sizeString.length - 1];
                    sizes.add(size);
                }
            }
        }
        return sizes;
    }
}
