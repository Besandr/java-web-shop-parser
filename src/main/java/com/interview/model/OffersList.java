package com.interview.model;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "offers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OffersList {
    private static OffersList instance = new OffersList();


    @Getter
    @XmlElement(name = "offer")
    private static volatile List<Offer> offers = new ArrayList<>();

    private OffersList(){}

    public static synchronized OffersList getInstance() {
        return instance;
    }

    public synchronized void add(Offer offer) {
        offers.add(offer);
    }



}
