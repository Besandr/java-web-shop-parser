package com.besandr.parsers.offerpropertiesparsers;

import com.besandr.model.properties.Brand;
import com.besandr.model.properties.Property;
import com.besandr.util.Const;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class BrandParser implements PropertyParser {

    @Override
    public Property parse(Document offerPage) {
        String brand;
        Elements nameClass = offerPage.getElementsByClass(Const.BRAND_CLASS);
        // Target brand&name text is always first (and single) element in Elements nameClass
        // brand&name string parrern is: "Produktinfos: offerBrand | offerName"
        String[] brandAndName = nameClass.get(0).text().split("[|]");
        int colonIndex = brandAndName[0].indexOf(":");
        brand = brandAndName[0].substring(colonIndex + 1).trim();
        return new Brand(brand);
    }
}
