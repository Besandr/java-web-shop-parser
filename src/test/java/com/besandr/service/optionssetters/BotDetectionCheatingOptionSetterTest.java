package com.besandr.service.optionssetters;

import com.besandr.service.AppSettings;
import com.besandr.service.options.BotDetectionCheatingOption;
import org.junit.Assert;
import org.junit.Test;

public class BotDetectionCheatingOptionSetterTest {
    @Test
    public void setOptionTest() {
        new BotDetectionCheatingOptionSetter().setOption("on");
        Assert.assertEquals(BotDetectionCheatingOption.ON, AppSettings.getBotDetectionCheating());
    }
}
