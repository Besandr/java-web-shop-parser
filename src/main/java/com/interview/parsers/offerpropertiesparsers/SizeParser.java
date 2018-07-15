package com.interview.parsers.offerpropertiesparsers;

import com.interview.model.properties.Property;
import com.interview.model.properties.Size;
import com.interview.model.properties.Sizes;
import com.interview.util.Const;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class SizeParser implements PropertyParser {

    @Override
    public Property parse(Document offerPage) {

        ArrayList<Size> sizes = new ArrayList<>();
        Elements sizeElements = offerPage.getElementsByAttributeValueContaining("class", Const.SIZE_KEY);
        if (!sizeElements.isEmpty()) {
            for (Element sizeElement :
                    sizeElements) {
                // Check for sold out sizes
                if (!sizeElement.text().contains(Const.SOLD_SIZE_KEY)) {
                    sizes.add(new Size(sizeElement.text()));
                }
            }
        } else {
            // If upper step didn't find sizes then offer has only one size (like lipstick, perfume or bag etc.)
            // Lets find it
            sizeElements = offerPage.getElementsByAttributeValueContaining("class", Const.ONE_SIZE_KEY);
            if (!sizeElements.isEmpty()) {
                sizes.add(new Size(sizeElements.get(0).text()));
            }
        }

        return new Sizes(sizes);
    }
}
