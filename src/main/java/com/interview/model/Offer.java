package com.interview.model;

import com.interview.Util.OutputManager;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class Offer {
    private String name;
    private String brand;
    private String color;
    private BigDecimal price;
    private BigDecimal initialPrice;
    private String description;
    private String articleID;
    private BigDecimal shippingCost;
    private List<String> sizes;

    private OffersList savePath;

    public Offer(OffersList offersList) {
        savePath = offersList;
    }


    public void save(){
        savePath.add(this);
//        System.out.println("\n\nobject " + color + " saved!!\n\n");
//        OutputManager.write(OffersList.getOffers(), "d:\\result.xml");
    }



}
