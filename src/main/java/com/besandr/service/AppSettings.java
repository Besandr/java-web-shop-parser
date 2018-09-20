package com.besandr.service;

import com.besandr.parsers.offerpropertiesparsers.*;
import com.besandr.service.options.BotDetectionCheatingOption;
import com.besandr.service.options.ConcurrencyOption;
import com.besandr.service.optionssetters.BotDetectionCheatingOptionSetter;
import com.besandr.service.optionssetters.ConcurrencyOptionSetter;
import com.besandr.service.optionssetters.OptionSetter;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

@Setter
public final class AppSettings {
    private static final Logger logger = LogManager.getLogger(AppSettings.class);

    @Setter
    private static Path settingsFile = Paths.get("./Settings.ini");
    public static File targetFile = new File("./offers.xml");

    // Default values of application's settings
    @Setter @Getter
    private static ConcurrencyOption concurrency = ConcurrencyOption.DEFAULT.getDefault();
    @Setter @Getter
    private static BotDetectionCheatingOption botDetectionCheating = BotDetectionCheatingOption.DEFAULT.getDefault();

    private static final HashMap<String, OptionSetter> optionsSettersMap = new HashMap<>();
    static {
        optionsSettersMap.put("concurrency", new ConcurrencyOptionSetter());
        optionsSettersMap.put("bot detection cheating", new BotDetectionCheatingOptionSetter());
    }

    // List of offers properties which need to be parsed
    public static PropertyParser[] propertiesList =
            {
                    new NameParser(),
                    new BrandParser(),
                    new ColorParser(),
                    new SizeParser(),
                    new PriceParser(),
                    new InitialPriceParser(),
                    new CurrencyParser(),
                    new DescriptionParser(),
                    new ArticleIDParser(),
                    new ShippingCostParser()
            };



    public static void setUpSettings(){

        try {
            List<String> settingsLines = Files.readAllLines(settingsFile);
            for (String option : optionsSettersMap.keySet()) {
                for (String settingLine : settingsLines) {
                    if (settingLine.contains("=") && settingLine.charAt(0) != '#') {
                        String[] splittedLine = settingLine.split("=");
                        String optionName = splittedLine[0].trim().toLowerCase();
                        if (option.equals(optionName)) {
                            String optionValue = splittedLine[1].trim().toLowerCase();
                            optionsSettersMap.get(option).setOption(optionValue);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            logger.error("There is no configuration file Settings.ini . Using default settings!");
        } catch (IOException e) {
            logger.error("Can't access to the settings file");
        }
    }

}
