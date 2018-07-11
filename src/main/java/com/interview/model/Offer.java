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
public class Offer {
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

    public Offer(){}


    public void save(){
        if (!sizes.isEmpty()) {
            for (int i = 0; i < sizes.size(); i++) {
                this.size = sizes.get(i);
                logger.info(i + "  ===  " + this.size);
                savePath.add(this);
            }
        } else {
            savePath.add(this);
        }

//        System.out.println("\n\nobject " + color + " saved!!\n\n");
//        OutputManager.write(OffersList.getOffers(), "d:\\result.xml");
    }



}
