package com.interview.parsers.offerpropertiesparsers;

import com.interview.model.properties.Name;
import com.interview.model.properties.Property;
import com.interview.util.Const;
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
