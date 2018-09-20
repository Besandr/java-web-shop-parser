package com.besandr.service.options;

public enum BotDetectionCheatingOption implements Option {
    ON, OFF, DEFAULT;

    @Override
    public BotDetectionCheatingOption getDefault() {
        return OFF;
    }
}
