package com.macmil.framework.webdriver.browser;

import com.macmil.framework.config.Config;
import com.macmil.framework.webdriver.exceptions.WebDriverCreationException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Creates a web driver for multiple browser types allowing for multi-browser tests
 */
public enum BrowserType {
    CHROME {
        @Override
        public WebDriver createDriver(Config config) {
            try {
                return new RemoteWebDriver(new URL(config.getRemoteUri()), new ChromeOptions());
            } catch (MalformedURLException e) {
                throw new WebDriverCreationException("Failed to create WebDriver", e);
            }
        }
    },
    FIREFOX{
        @Override
        public WebDriver createDriver(Config config) {
            throw new IllegalStateException("This is unimplemented example of potential extension to browser type enum");
        }
    };

    /**
     * Creates the web driver
     * @param config browser config
     * @return web driver
     */
    public abstract WebDriver createDriver(Config config);
}
