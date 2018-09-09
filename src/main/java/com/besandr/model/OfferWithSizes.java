package com.besandr.model;

import com.besandr.model.properties.*;
import com.besandr.util.resultwriters.XMLWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class OfferWithSizes {

    private static final Logger logger = LogManager.getLogger(OfferWithSizes.class);

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
            // If there is no sale for this offer current price assumes as initial price
            if (((InitialPrice) property).getInitialPrice() != null) {
                initialPrice = (InitialPrice) property;
            } else {
                initialPrice = new InitialPrice(price.getPrice());
            }
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
                XMLWriter.writeOffer(offer);
            }
        }
    }
}
