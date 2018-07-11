package com.interview.util;

import com.interview.model.OffersList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class XMLWriter {
    private static final Logger logger = LogManager.getLogger(XMLWriter.class);

    public static void writeResults() {

        try {

            File file = new File("./offers.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(OffersList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //override basic header
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            jaxbMarshaller.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

            jaxbMarshaller.marshal(OffersList.getInstance(), file);
            logger.info("Offers have been written successfully");

        } catch (JAXBException e) {
            logger.error(e.getMessage() + e.getStackTrace());
        }
    }


}
