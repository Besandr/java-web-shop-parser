package com.interview.model;


import com.interview.parsers.OfferParser;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Offer implements Cloneable {
    private static final Logger logger = LogManager.getLogger(OfferParser.class);
    private String name;
    private String brand;
    private String color;
    private String size;
    private BigDecimal price;
    private BigDecimal initialPrice;
    private String currency;
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

    // JAXB(xml) requires a default constructor
    public Offer(){}

    // Constructor for cloning Offer
    private Offer(String name, String brand, String color, String size,
                 BigDecimal price, BigDecimal initialPrice,
                 String currency, String description, String articleID,
                 BigDecimal shippingCost) {
        this.name = name;
        this.brand = brand;
        this.color = color;
        this.size = size;
        this.price = price;
        this.initialPrice = initialPrice;
        this.currency = currency;
        this.description = description;
        this.articleID = articleID;
        this.shippingCost = shippingCost;
    }

    public void save(){
        if (!sizes.isEmpty()) {
            size = sizes.get(0);
            savePath.add(this);
            // If sizes more than one copying offer, setting new size for it
            // and saving new instance
            for (int i = 1; i < sizes.size(); i++) {
                Offer offerWithOtherSize = offerCopy(this);
                offerWithOtherSize.size = sizes.get(i);
                savePath.add(offerWithOtherSize);
            }
        } else {
            savePath.add(this);
        }
    }

    private Offer offerCopy (Offer source) {
        return new Offer(source.name,
                source.brand,
                source.color,
                source.size,
                source.price,
                source.initialPrice,
                source.currency,
                source.description,
                source.articleID,
                source.shippingCost);

    }

}
