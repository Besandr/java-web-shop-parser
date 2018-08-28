package com.besandr.parsers.offerpropertiesparsers;

import com.besandr.model.properties.Name;
import com.besandr.model.properties.Property;
import com.besandr.util.Const;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class NameParser implements PropertyParser {

    @Override
    public Property parse(Document offerPage) {
        String name;
        Elements nameClass = offerPage.getElementsByClass(Const.NAME_CLASS);
        String[] brandAndName = nameClass.get(0).text().split("[|]"); // Target brand&name text is always first element in Elements nameClass
        name = brandAndName[1].trim();                     // brand&name string pattern is: "Produktinfos: offerBrand | offerName"
        return new Name(name);
    }
}
