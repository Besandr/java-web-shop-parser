package com.besandr.service;


import com.besandr.parsers.offerpropertiesparsers.*;

public final class AppSettings {
    public enum CONC_MODE {ONE_THREAD, MULTI_THREAD}
    public static CONC_MODE concurrency;

    public enum REQ_MODE {BOOST, LAZY}
    public static REQ_MODE requesting_mode;


    // List of offers properties (from a task)
    public static PropertyParser[] propertiesList =
            {
                    new NameParser(),
                    new BrandParser(),
                    new ColorParser(),
                    new SizeParser(),
                    new PriceParser(),
                    new InitialPriceParser(),
                    new CurrencyParser(),
                    new DescriptionParser(),
                    new ArticleIDParser(),
                    new ShippingCostParser()
            };
}
