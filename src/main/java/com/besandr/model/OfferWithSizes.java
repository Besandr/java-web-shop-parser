package com.besandr.model;

import com.besandr.model.properties.*;
import com.besandr.parsers.OfferParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class OfferWithSizes {

    private static final Logger logger = LogManager.getLogger(OfferParser.class);

    private Name name;
    private Brand brand;
    private Color color;
    private Price price;
    private InitialPrice initialPrice;
    private Currency currency;
    private Description description;
    private ArticleID articleID;
    private ShippingCost shippingCost;

    private Sizes sizes;

    private OffersList savePath;

    public OfferWithSizes(OffersList savePath) {
        this.savePath = savePath;
    }


    public void setProperty(Property property) {
        if (property instanceof Name) {
            name = (Name) property;
        }
        if (property instanceof Brand) {
            brand = (Brand) property;
        }
        if (property instanceof Color) {
            color = (Color) property;
        }
        if (property instanceof Price) {
            price = (Price) property;
        }
        if (property instanceof InitialPrice) {
            initialPrice = (InitialPrice) property;
        }
        if (property instanceof Currency) {
            currency = (Currency) property;
        }
        if (property instanceof Description) {
            description = (Description) property;
        }
        if (property instanceof ArticleID) {
            articleID = (ArticleID) property;
        }
        if (property instanceof ShippingCost) {
            shippingCost = (ShippingCost) property;
        }
        if (property instanceof Sizes) {
            sizes = (Sizes) property;
        }
    }


    public void save(){


        if (!sizes.getSizes().isEmpty()) {
            // Creating the new offer in offersList for each size of current offer
            for (int i = 0; i < sizes.getSizes().size(); i++) {
                Offer offer = new Offer(
                        name,
                        brand,
                        color,
                        sizes.getSizes().get(i),
                        price,
                        initialPrice,
                        currency,
                        description,
                        articleID,
                        shippingCost
                );
                savePath.add(offer);
            }
        }
    }
}
