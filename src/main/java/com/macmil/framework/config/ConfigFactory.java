package com.macmil.framework.config;

/**
 * Factory generating Config object
 */
public class ConfigFactory {
    private static final String BROWSER_TYPE = ConfigReader.getConfig().getString("browser.type");
    private static final String BROWSER_VIEWPORT = ConfigReader.getConfig().getString("browser.viewport");
    private static final String REMOTE_URI = ConfigReader.getConfig().getString("browser.remote_uri");
    private static final String TEST_URL = ConfigReader.getConfig().getString("test.url");

    private ConfigFactory() {}

    /**
     * Generates test configuration
     * @return test configuration
     */
    public static Config getConfig() {
        return Config.builder()
                .browserType(BROWSER_TYPE)
                .browserViewport(BROWSER_VIEWPORT)
                .remoteUri(REMOTE_URI)
                .testUrl(TEST_URL)
                .build();
    }
}
