package com.interview.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class OffersList {
    private static OffersList instance = new OffersList();

    @Getter
    public static volatile List<Offer> offers = new ArrayList<>();

    private OffersList(){}

    public static synchronized OffersList getInstance() {
        return instance;
    }

    public synchronized void add(Offer offer) {
        offers.add(offer);
    }



}
