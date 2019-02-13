package com.besandr.parsers.offerpropertiesparsers;

import com.besandr.model.properties.Name;
import com.besandr.model.properties.Property;
import com.besandr.util.Const;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**Name parser finds a tag with a class stored in Const.NAME_CLASS
 * This tags text contains brand&name with a pattern: "Produktinfos: offerBrand | offerName"
 * Parser splits this string and receives a name
 */

public class NameParser implements PropertyParser {

    @Override
    public Property parse(Document offerPage) {
        String name = null;
        Elements nameClass = offerPage.getElementsByClass(Const.NAME_CLASS);
        String[] brandAndName = nameClass.get(0).text().split("[|]");
        name = brandAndName[1].trim();
        return new Name(name);
    }
}
