package com.macmil.framework.model;

import com.macmil.framework.webdriver.DriverManager;
import com.macmil.framework.webdriver.browser.dsl.Browser;
import org.openqa.selenium.WebDriver;

/**
 * Base class in page object model from which all page classes derive
 * @param <V> page object model class
 */
public abstract class BasePageObject<V> implements PageVariant {
    private static Browser browser;

    /**
     * Obtains WebDriver instance via DriverManager
     * @return WebDriver instance
     */
    public WebDriver getDriver() { return DriverManager.getInstance().getDriver(); }

    /**
     * Obtains Browser instance
     * @return Browser instance
     */
    public static Browser getBrowser() {
        if (browser == null) {
            browser = new Browser();
        }
        return browser;
    }

    /**
     * Syntactic glue useful for sentence like test statements
     * @return itself
     */
    @SuppressWarnings(value = "unchecked")
    public V and() { return (V) this; }
}
