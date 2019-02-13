package com.besandr.parsers.offerpropertiesparsers;

import com.besandr.model.properties.Brand;
import com.besandr.model.properties.Property;
import com.besandr.util.Const;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**Brand parser finds a tag with a class stored in Const.BRAND_CLASS
 * This tags text contains brand&name with a pattern: "Produktinfos: offerBrand | offerName"
 * Parser splits this string, removes a "Produktinfos:" part and receives a brand
 */

public class BrandParser implements PropertyParser {

    @Override
    public Property parse(Document offerPage) {
        String brand = null;
        Elements nameClass = offerPage.getElementsByClass(Const.BRAND_CLASS);
        String[] brandAndName = nameClass.get(0).text().split("[|]");
        int colonIndex = brandAndName[0].indexOf(":");
        brand = brandAndName[0].substring(colonIndex + 1).trim();
        return new Brand(brand);
    }
}
