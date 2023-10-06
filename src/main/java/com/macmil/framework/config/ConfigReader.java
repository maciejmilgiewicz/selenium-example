package com.macmil.framework.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Test config reader
 */
public final class ConfigReader {
    private static final String DEFAULT_CONFIG = "config/defaults";
    private static final Config CONFIG = ConfigFactory.load(DEFAULT_CONFIG);

    private ConfigReader() {}

    /**
     * Obtains test configuration as read in CONFIG
     * @return test configuration
     */
    public static Config getConfig() { return CONFIG; }
}
