package com.interview.model;

import com.interview.util.OutputManager;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Offer {
    private String name;
    private String brand;
    private String color;
    private String size;
    private BigDecimal price;
    private BigDecimal initialPrice;
    private String description;
    private String articleID;
    private BigDecimal shippingCost;
    @XmlTransient
    private List<String> sizes;


    @XmlTransient
    private OffersList savePath;

    public Offer(OffersList offersList) {
        savePath = offersList;
    }

    public Offer(){}


    public void save(){
        if (!sizes.isEmpty()) {
            for (int i = 0; i < sizes.size(); i++) {
                size = sizes.get(i);
                savePath.add(this);
            }
        } else {
            savePath.add(this);
        }

//        System.out.println("\n\nobject " + color + " saved!!\n\n");
//        OutputManager.write(OffersList.getOffers(), "d:\\result.xml");
    }



}
