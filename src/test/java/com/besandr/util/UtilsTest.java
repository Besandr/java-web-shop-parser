package com.besandr.util;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class UtilsTest {

    @Test
    public void costFromStringTest() {
        String testString = "34,90€";
        BigDecimal expectedResult = new BigDecimal("34.90");
        Assert.assertEquals(expectedResult, Utils.getCostFromString(testString));
    }

    @Test
    public void currencyDefinerTest() {
        String testString = "34,90€";
        String expectedString = "EUR";
        Assert.assertEquals(expectedString, Utils.currencyDefiner(testString));
    }
}
