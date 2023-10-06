package com.macmil.framework.webdriver.browser.dsl.elements;

import org.openqa.selenium.WebElement;

/**
 * Representation of text field
 */
public class TextField extends PageElement {

    public TextField(WebElement webElement) { super(webElement); }

    /**
     * Types text into text field
     * @param text text to type in
     */
    public void type(String text) {
        if (text != null) {
            focus();
            getWebElement().clear();
            getWebElement().sendKeys(text);
        }
    }
}
