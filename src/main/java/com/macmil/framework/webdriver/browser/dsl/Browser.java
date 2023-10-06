package com.macmil.framework.webdriver.browser.dsl;

import com.macmil.framework.webdriver.DriverManager;
import com.macmil.framework.webdriver.browser.dsl.elements.PageElement;
import com.macmil.framework.webdriver.browser.dsl.elements.TextField;
import com.macmil.framework.webdriver.browser.dsl.window.WindowManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Controls the browser
 */
public class Browser {
    private static final long TIMEOUT = 20;

    private final WebDriver driver;
    private final WindowManager window;

    /**
     * Constructor setting driver via DriverManager and creating a new instance of WindowManager
     */
    public Browser() {
        driver = DriverManager.getInstance().getDriver();
        window = new WindowManager(driver);
    }

    /**
     * Obtains a page element
     * @param webElement WebElement instance
     * @return a page element
     */
    public PageElement getElement(WebElement webElement) { return new PageElement(webElement); }

    /**
     * Obtains a button
     * @param webElement WebElement instance
     * @return a button
     */
    public PageElement getButton(WebElement webElement) { return new PageElement(webElement); }

    /**
     * Obtains a text field
     * @param webElement WebElement instance
     * @return a text field
     */
    public TextField getTextField(WebElement webElement) { return new TextField(webElement); }

    /**
     * Opens given ulr
     * @param url URL
     */
    public void open(String url) { window.open(url); }

    /**
     * Waits up to TIMEOUT seconds for page to load
     */
    public void waitForPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT)).until(input -> isPageComplete(getReadyState(driver)));
    }

    /**
     * Waits an arbitrary number of seconds for page action to happen
     * @param seconds number of seconds to wait
     */
    public void waitForPageAction(long seconds) {
        window.waitForWindowAction(seconds);
    }

    private boolean isPageComplete(String readyState) { return "complete".equals(readyState); }

    private String getReadyState(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.readyState;");
    }
}
