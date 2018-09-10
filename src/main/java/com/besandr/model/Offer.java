package com.besandr.model;


import com.besandr.model.properties.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Offer implements Cloneable {

    private Name name;
    private Brand brand;
    private Color color;
    private Size size;
    private Price price;
    private InitialPrice initialPrice;
    private Currency currency;
    private Description description;
    private ArticleID articleID;
    private ShippingCost shippingCost;

    public Offer(Name name, Brand brand, Color color, Size size, Price price, InitialPrice initialPrice, Currency currency, Description description, ArticleID articleID, ShippingCost shippingCost) {
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

    public Property[] getAllProperties() {
        Property[] allProperties = {
                name,
                brand,
                color,
                size,
                price,
                initialPrice,
                currency,
                description,
                articleID,
                shippingCost
        };
        return allProperties;
    }
}
