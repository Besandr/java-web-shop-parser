package com.besandr.parsers.offerpropertiesparsers;

import com.besandr.model.properties.Property;
import com.besandr.model.properties.Size;
import com.besandr.model.properties.Sizes;
import com.besandr.util.Const;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**Size parser finds a tag which class attribute contains the fragment stored in Const.SIZE_KEY.
 * There are sold out sizes in offers so parser checks out every size. If the size isn't sold out parser
 * adds it to the list of sizes.
 */

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
            // If previous step didn't find sizes then offer has only one size (like lipstick, perfume or bag etc.)
            // Lets find it
            sizeElements = offerPage.getElementsByAttributeValueContaining("class", Const.ONE_SIZE_KEY);
            if (!sizeElements.isEmpty()) {
                sizes.add(new Size(sizeElements.get(0).text()));
            }
        }

        return new Sizes(sizes);
    }
}
