package com.macmil.framework.webdriver;

import com.macmil.framework.webdriver.browser.BrowserType;
import com.macmil.framework.webdriver.browser.BrowserViewport;
import com.macmil.framework.config.Config;
import com.macmil.framework.config.ConfigFactory;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.util.Optional;

/**
 * WebDriver manager
 */
@Slf4j
public class DriverManager {
    private static final DriverManager INSTANCE = new DriverManager();
    private static final ThreadLocal<WebDriver> registry = new ThreadLocal<>();

    private DriverManager() {}

    /**
     * Returns an instance of DriverManager
     * @return DriverManager instance
     */
    public static DriverManager getInstance() { return INSTANCE; }

    /**
     * Creates a WebDriver instance of configured browser type
     * @return WebDriver instance
     */
    public WebDriver getDriver() {
        WebDriver driver = registry.get();

        if (driver == null) {
            Config config = ConfigFactory.getConfig();
            driver = createDriver(config);
            registry.set(driver);
            setBrowserViewport(driver, getBrowserViewport(config));
        }

        return driver;
    }

    /**
     * Destroys a WebDriver instance
     */
    public void destroyDriver() {
        WebDriver driver = registry.get();

        if (driver != null) {
           log.info("Destroying WebDriver instance");

           try {
               driver.quit();
           } catch (WebDriverException e) {
               log.debug("Exception occurred while closing browser!", e);
           }
           registry.remove();

           log.info("WebDriver instance destroyed successfully");
        } else {
            log.warn("WebDriver instance is not available, unable to destroy!");
        }
    }

    private WebDriver createDriver(Config config) {
        WebDriver retval;

        log.info("Creating WebDriver instance [driver: {}, browser: {}]", config.getRemoteUri(), config.getBrowserType());
        retval = BrowserType.valueOf(config.getBrowserType()).createDriver(config);
        log.info("WebDriver instance created successfully");

        return retval;
    }

    private void setBrowserViewport(WebDriver driver, BrowserViewport viewport) {
        log.info("Setting browser viewport to {}", viewport);
        Optional<Dimension> windowSize = viewport.getDimention();
        if(windowSize.isPresent()) {
            driver.manage().window().setSize(windowSize.get());
        } else {
            try {
                driver.manage().window().maximize();
            } catch (WebDriverException e) {
                log.warn("WebDriver issue while maximising browser window");
            }
        }
    }

    private BrowserViewport getBrowserViewport(Config config) {
        return BrowserViewport.valueOf(config.getBrowserViewport());
    }
}
