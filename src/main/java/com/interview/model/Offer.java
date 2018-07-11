package com.interview.model;

import com.interview.Util.OutputManager;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Offer {
    private String name;
    private String brand;
    private String color;
    private String price;
    private String initialPrice;
    private String description;
    private String articleID;
    private String shippingCost;
    private List<String> sizes;

    private OffersList savePath;

    public Offer(OffersList offersList) {
        savePath = offersList;
    }


    public void save(){
        savePath.add(this);
//        OutputManager.write(OffersList.getOffers(), "d:\\result.xml");
    }



}
