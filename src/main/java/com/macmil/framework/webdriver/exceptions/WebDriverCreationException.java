package com.macmil.framework.webdriver.exceptions;

/**
 * WebDriver creation exception
 */
public class WebDriverCreationException extends RuntimeException {

    public WebDriverCreationException(String message) { super(message); }

    public WebDriverCreationException(String message, Throwable reason) { super(message, reason); }
}
