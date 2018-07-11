package com.interview.parsers;

import com.interview.Util.Const;
import com.interview.Util.Utils;
import com.interview.model.Offer;
import com.interview.model.OffersList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OfferParser extends AbstractParser {

//    File htmlFile = new File("D:\\1.htm");

    private Document offerPage;
    private Offer offer;
    private boolean isExtractionRequired;
    private List<String> restColorPages = new ArrayList<>();


    public OfferParser (String offerURL, boolean isExtractionRequired){
        this.isExtractionRequired = isExtractionRequired;
        try {
            offerPage = Jsoup.connect(offerURL).get();
//            offerPage = Jsoup.parse(htmlFile, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {

        super.run();
        if (isExtractionRequired) {
            colorPagesFinder();
            restColorsParseStarter();
        }

        // Parsing current offer page
        offerCreator();

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
        Utils.getthreadsPool().remove(this);
    }



//    public static void main(String[] args) {
//        OfferParser parser = new OfferParser(null, true);
//        parser.run();
//    }

    private void restColorsParseStarter() {
        if (!restColorPages.isEmpty()) {
            for (int i = 0; i < restColorPages.size(); i++) {
                if (Utils.getOfferLinksSet().add(restColorPages.get(i))) {
                    Thread offerParser = new OfferParser(restColorPages.get(i), false);
                    offerParser.start();
                }
            }
        }
    }

    private void colorPagesFinder() {
        Elements colorElements = offerPage.getElementsByClass(Const.COLOR_CLASS_KEY);
        for (Element colorClass : colorElements) {
            Elements color = colorClass.select("a[href]");
            String offerLink = color.get(0).attr("href"); // Target color link is always first element in Elements color
            String offerURL = Const.SITE_URL + offerLink;
            restColorPages.add(offerURL);
        }
    }

    private void offerCreator() {
        System.out.println("\nParsing  " + offerPage.baseUri() + " by  " + this.getClass().getSimpleName());
        offer = new Offer(OffersList.getInstance());
        offer.setName(parseName());
        offer.setBrand(parseBrand());
        offer.setColor(parseColor());
        offer.setPrice(parsePrice());
        offer.setInitialPrice(parseInitialPrice());
        offer.setDescription(parseDescription());
        offer.setArticleID(parseArticleID());
        offer.setShippingCost(parseShippingCost());
        offer.setSizes(parseSizes());
        offer.save();
    }

    private String parseName() {
        String name;
        Elements nameClass = offerPage.getElementsByClass(Const.NAME_CLASS);
        String[] brandAndName = nameClass.get(0).text().split("[|]"); // Target brand&name text is always first element in Elements nameClass
        name = brandAndName[1].trim();                     // brand&name string parrern is: "Produktinfos: offerBrand | offerName"
        return name;
    }

    private String parseBrand() {
        String brand;
        Elements nameClass = offerPage.getElementsByClass(Const.BRAND_CLASS);
        // Target brand&name text is always first (and single) element in Elements nameClass
        // brand&name string parrern is: "Produktinfos: offerBrand | offerName"
        String[] brandAndName = nameClass.get(0).text().split("[|]");
        int colonIndex = brandAndName[0].indexOf(":");
        brand = brandAndName[0].substring(colonIndex + 1).trim();
        return brand;
    }

    private String parseColor() {
        String color;
        String colorTitleTag = offerPage.title();
        //Title pattern is "offerBrand offerName in offerColor | ABOUT YOU"
        //There is no split symbols between Brand and Name so parsing for them in title is too risky
        //I parse only color here
        String colorAndSite = colorTitleTag.split(" in ")[1];
        color = colorAndSite.split("[|]")[0].trim();

        return color;
    }

    private BigDecimal parsePrice() {
        // Price can be parsed from the JSON request on the page, but I want to keep
        // price format presented on the page.

        String price;
        Elements priceDiv = offerPage.getElementsByAttributeValueContaining("class", Const.PRICE_KEY);
        // Target price tag is always first (and single) element in Elements nameClass
        // priceText string parrern is "ab "(optionally) with wanted price and currency type
        String priceText = priceDiv.get(0).text();

        return getCostFromString(priceText);
    }

    private BigDecimal parseInitialPrice() {
        BigDecimal initialPrice = null;
        // Some offers doesn't have an initial price, so default return value is null
        // But if it has it, the div tag will contain only text with initial Price
        Elements initialPriceDiv = offerPage.getElementsByAttributeValueContaining("class", Const.INITIAL_PRICE_KEY);
        if (initialPriceDiv.size() != 0) {
            initialPrice = getCostFromString(initialPriceDiv.get(0).text());
        }
        return initialPrice;
    }

    private String parseDescription() {
        String description = null;
        Elements descr = offerPage.getElementsByAttributeValueContaining("class", Const.DESCRIPTION_KEY);
        if (descr.size() != 0) {
            //Receiving the text of description and removing the ArticleID from it
            description = descr.text().
                    replace(Const.ARTICLE_KEYWORD + parseArticleID(), "").
                    trim();
        }
        return description;
    }

    private String parseArticleID() {
        String articleID;
        // Target tag is unique and it has a text field filled with such pattern: "Artikel-Nr: offerArticleID"
        Elements articleElement = offerPage.getElementsContainingOwnText(Const.ARTICLE_KEYWORD);
        articleID = articleElement.get(0).text().split(":")[1].trim();
        return articleID;
    }

    private BigDecimal parseShippingCost() {
        BigDecimal shippingCost;
        Elements shipCostElement = offerPage.getElementsByClass(Const.SHIPPING_COSTS_CLASS);
        shippingCost = getCostFromString(shipCostElement.get(0).text());
        //        String costString = shipCostElement.get(0).text();
//        int beginning = costString.indexOf("+") + 1;
//        int end = costString.indexOf("Versand");
//        shippingCost = costString.substring(beginning, end).trim();
        return shippingCost;
    }

    private List<String> parseSizes() {

        ArrayList<String> sizes = new ArrayList<>();
        Elements sizeElements = offerPage.getElementsByAttributeValueContaining("class", Const.SIZE_KEY);
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

    private BigDecimal getCostFromString(String cost) {
        return new BigDecimal(cost.replaceAll("[^0-9,]*", "")
                                    .replace("," , "."));
    }
}
