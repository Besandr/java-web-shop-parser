package com.besandr.service.optionssetters;

import com.besandr.service.AppSettings;
import com.besandr.service.options.ConcurrencyOption;
import com.besandr.service.optionssetters.ConcurrencyOptionSetter;
import org.junit.Assert;
import org.junit.Test;

public class ConcurrencyOptionSetterTest {
    @Test
    public void setOnOptionTest() {
        new ConcurrencyOptionSetter().setOption("on");
        Assert.assertEquals(ConcurrencyOption.MULTITHREAD, AppSettings.getConcurrency());
    }

    @Test
    public void setOffOptionTest() {
        new ConcurrencyOptionSetter().setOption("off");
        Assert.assertEquals(ConcurrencyOption.ONE_THREAD, AppSettings.getConcurrency());
    }
}
