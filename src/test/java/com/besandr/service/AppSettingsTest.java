package com.besandr.service;

import com.besandr.service.options.BotDetectionCheatingOption;
import com.besandr.service.options.ConcurrencyOption;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AppSettingsTest {

    Path testSettingsFilePath = Paths.get("./src/test/testResources/testSettings.properties");

    @Before
    public void setUp(){
        AppSettings.setSettingsFile(testSettingsFilePath);
        AppSettings.setUpSettings();
    }

    @Test
    public void setOptionsTest() {
        Assert.assertEquals(ConcurrencyOption.ONE_THREAD, AppSettings.getConcurrency());
    }

    @Test
    public void setBodDetectionCheatingTest() {
        Assert.assertEquals(BotDetectionCheatingOption.ON, AppSettings.getBotDetectionCheating());
    }

}
