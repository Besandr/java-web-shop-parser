package com.interview.util;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SetsHolder {

    public static final Set<String> OFFER_LINKS_SET = ConcurrentHashMap.newKeySet();

    public static final Set<Thread> THREADS_POOL = ConcurrentHashMap.newKeySet();


}


//    public void setProperty (Property property) {
//        if (property instanceof Name) {
//            this.name = (Name) property;
//        }
//    }

//    private Name name;

//    // Constructor for cloning Offer
//    private Offer(Name name, String brand, String color, String size,
//                  BigDecimal price, BigDecimal initialPrice,
//                  String currency, String description, String articleID,
//                  BigDecimal shippingCost) {
//        this.name = name;
//        this.brand = brand;
//        this.color = color;
//        this.size = size;
//        this.price = price;
//        this.initialPrice = initialPrice;
//        this.currency = currency;
//        this.description = description;
//        this.articleID = articleID;
//        this.shippingCost = shippingCost;
//    }



//offer.setProperty(new NameParser(offerPage).parse());

