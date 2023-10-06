package com.macmil.framework.webdriver.browser.dsl.elements;

import com.google.common.base.Preconditions;
import com.macmil.framework.webdriver.DriverManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Representation of website page element
 */
@Slf4j
public class PageElement {
    private static final long TIMEOUT = 20;

    @Getter
    private final WebElement webElement;
    private final WebDriver driver;

    /**
     * Constructor taking WebElement parameter and setting driver via DriverManager
     * @param webElement WebElement object
     */
    public PageElement(WebElement webElement) {
        this.webElement = webElement;
        driver = DriverManager.getInstance().getDriver();
    }

    /**
     * Clicks on page element
     * @return itself
     */
    public PageElement click() {
        Preconditions.checkState(isEnabled(), String.format("Element %s could not be clicked, since it's not enabled!", webElement.toString()));
        focus();
        webElement.click();
        return this;
    }

    /**
     * Focuses on given page element
     * @return itself
     */
    public PageElement focus() {
        new Actions(driver).moveToElement(webElement).perform();
        waitForDisplayed();
        return this;
    }

    public String getText() {
        return webElement.getText();
    }

    /**
     * Waits up to TIMEOUT seconds for the element to be enabled
     * @return itself
     */
    public PageElement waitForEnabled() {
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT)).until(input -> isEnabled());
        return this;
    }

    /**
     * Checks if given web element is enabled on the page
     * @return true if enabled, false otherwise
     */
    public boolean isEnabled() {
        boolean retval;
        try {
            retval = webElement.isEnabled();
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            log.debug("Element is not enabled!", e);
            retval = false;
        }

        return retval;
    }

    /**
     * Waits up to TIMEOUT seconds for the element to be displayed
     * @return itself
     */
    public PageElement waitForDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT)).until(input -> isDisplayed());
        return this;
    }

    /**
     * Waits up to TIMEOUT seconds for the element not to be displayed
     * @return itself
     */
    public PageElement waitForNotDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT)).until(input -> !isDisplayed());
        return this;
    }

    /**
     * Checks if given web element is displayed on the page
     * @return true if displayed, false otherwise
     */
    public boolean isDisplayed() {
        boolean retval;
        try {
            retval = webElement.isDisplayed();
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            log.debug("Element is not displayed!", e);
            retval = false;
        }

        return retval;
    }

    /**
     * Syntactic glue useful for sentence like test statements
     * @return itself
     */
    public PageElement and() { return this; }
}
