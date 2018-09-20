package com.besandr.util.resultswriters;

import com.besandr.model.Offer;
import com.besandr.model.properties.*;
import com.besandr.service.AppSettings;
import com.besandr.util.resultwriters.XMLWriter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;

public class XMLWriterTest {

    Offer offer;

    @Before
    public void setUp() {
        offer = new Offer(
                new Name("testName"),
                new Brand("testBrand"),
                new Color("testColor"),
                new Size("testSize"),
                new Price(BigDecimal.ONE),
                new InitialPrice(BigDecimal.TEN),
                new Currency("testCurrency"),
                new Description("testDescription"),
                new ArticleID("testArticleID"),
                new ShippingCost(BigDecimal.ZERO)
        );
        XMLWriter.writeOffer(offer);
    }

    @After
    public void endTest() throws IOException {
        Files.delete(AppSettings.targetFile.toPath());
    }

    @Test
    public void fileCreationTest(){
        Assert.assertTrue("The app can't create a offers.xml file", Files.exists(AppSettings.targetFile.toPath()));
    }

    @Test
    public void writeOfferTest() {
        XMLWriter.writeOffer(offer);
        try (BufferedReader reader = new BufferedReader(new FileReader(AppSettings.targetFile))) {
            String propertyLine = "";
            String expectedLine = "        <color>testColor</color>";
            for (int i = 0; i < 4; i++) {
                propertyLine = reader.readLine();
            }
            Assert.assertEquals(expectedLine, propertyLine);
        } catch (IOException ex) {
            Assert.assertFalse("IOException was thrown", true);
        }
    }

}
