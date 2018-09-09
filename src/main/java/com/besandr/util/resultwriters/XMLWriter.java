package com.besandr.util.resultwriters;

import com.besandr.model.Offer;
import com.besandr.model.properties.Property;
import com.besandr.service.AppSettings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class XMLWriter {
    private static final Logger logger = LogManager.getLogger(XMLWriter.class);


    public static void writeHeaderElement() {
        try (FileWriter writer = new FileWriter(AppSettings.targetFile)) {

            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<offers>\n");

        } catch (IOException e) {
            logger.error("Can't access to the target file");
        }
    }

    public static void writeOffer(Offer offer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(AppSettings.targetFile, true))) {

            writer.write("    <offer>\n");
            for (Property property : offer.getAllProperties()) {
                if (property != null) {
                    StringBuilder propertyString = new StringBuilder();
                    propertyString.append("        <" + property.getName() + ">");
                    propertyString.append(property.toString());
                    propertyString.append("</" + property.getName() + ">");
                    propertyString.append("\n");
                    writer.write(propertyString.toString());
                }
            }
            writer.write("    </offer>\n");

        } catch (IOException e) {
            logger.error("Can't access to the target file");
        }
    }

    public static void writeEndElement() {
        try (FileWriter writer = new FileWriter(AppSettings.targetFile, true)) {

            writer.write("</offers>\n");

            logger.info("Offers have been written successfully\n");

        } catch (IOException e) {
            logger.error("Can't access to the target file");
        }
    }
}
