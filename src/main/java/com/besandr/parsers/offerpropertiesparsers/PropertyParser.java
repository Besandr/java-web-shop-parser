package com.besandr.parsers.offerpropertiesparsers;

import com.besandr.model.properties.Property;
import org.jsoup.nodes.Document;

public interface PropertyParser {

    Property parse(Document offerPage);

}
