package com.besandr.service.optionssetters;

import com.besandr.service.AppSettings;
import com.besandr.service.options.ConcurrencyOption;

public class ConcurrencyOptionSetter implements OptionSetter {

    @Override
    public void setOption(String optionValue) {
        switch (optionValue) {
            case "off":
                AppSettings.setConcurrency(ConcurrencyOption.ONE_THREAD);
                break;
            case "on":
                AppSettings.setConcurrency(ConcurrencyOption.MULTITHREAD);
                break;
            default:
                break;
        }
    }
}
