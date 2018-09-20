package com.besandr.service.optionssetters;

import com.besandr.service.AppSettings;
import com.besandr.service.options.BotDetectionCheatingOption;

public class BotDetectionCheatingOptionSetter implements OptionSetter {

    @Override
    public void setOption(String optionValue) {
        switch (optionValue) {
            case "on":
                AppSettings.setBotDetectionCheating(BotDetectionCheatingOption.ON);
                break;
            case "off":
                AppSettings.setBotDetectionCheating(BotDetectionCheatingOption.OFF);
                break;
            default:
                break;
        }
    }
}
