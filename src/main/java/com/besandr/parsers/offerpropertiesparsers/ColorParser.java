package com.besandr.parsers.offerpropertiesparsers;

import com.besandr.model.properties.Color;
import com.besandr.model.properties.Property;
import org.jsoup.nodes.Document;

public class ColorParser implements PropertyParser {

    @Override
    public Property parse(Document offerPage) {

        String color;
        String colorTitleTag = offerPage.title();
        //Title pattern is "offerBrand offerName in offerColor | ABOUT YOU"
        //There is no split symbols between Brand and Name so parsing for them in title is too risky
        //I parse only color here
        String colorAndSite = colorTitleTag.split(" in ")[1];
        color = colorAndSite.split("[|]")[0].trim();

        return new Color(color);
    }
}
