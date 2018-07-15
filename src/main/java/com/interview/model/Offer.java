package com.interview.model;


import com.interview.model.properties.*;
import com.interview.parsers.OfferParser;
import com.interview.util.outputadapters.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Getter
@Setter
@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Offer implements Cloneable {
    private static final Logger logger = LogManager.getLogger(OfferParser.class);

    @XmlJavaTypeAdapter(NameAdapter.class)
    private Name name;
    @XmlJavaTypeAdapter(BrandAdapter.class)
    private Brand brand;
    @XmlJavaTypeAdapter(ColorAdapter.class)
    private Color color;
    @XmlJavaTypeAdapter(SizeAdapter.class)
    private Size size;
    @XmlJavaTypeAdapter(PriceAdapter.class)
    private Price price;
    @XmlJavaTypeAdapter(InitialPriceAdapter.class)
    private InitialPrice initialPrice;
    @XmlJavaTypeAdapter(CurrencyAdapter.class)
    private Currency currency;
    @XmlJavaTypeAdapter(DescriptionAdapter.class)
    private Description description;
    @XmlJavaTypeAdapter(ArticleIDAdapter.class)
    private ArticleID articleID;
    @XmlJavaTypeAdapter(ShippingCostAdapter.class)
    private ShippingCost shippingCost;

    @XmlTransient
    private OffersList savePath;

    // JAXB(xml) requires a default constructor
    public Offer(){}

    Offer(Name name, Brand brand, Color color, Size size, Price price, InitialPrice initialPrice, Currency currency, Description description, ArticleID articleID, ShippingCost shippingCost) {
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

    public void setProperty(Property property) {
        if (property instanceof Name) {
            name = (Name) property;
        }
    }

}
