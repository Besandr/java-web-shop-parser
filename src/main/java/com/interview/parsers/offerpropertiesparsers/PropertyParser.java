package com.interview.parsers.offerpropertiesparsers;

import com.interview.model.properties.Property;
import org.jsoup.nodes.Document;

public interface PropertyParser {

    Property parse(Document offerPage);

}
