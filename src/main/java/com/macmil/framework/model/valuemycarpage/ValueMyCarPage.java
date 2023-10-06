package com.macmil.framework.model.valuemycarpage;

import com.macmil.framework.model.BasePageObject;
import com.macmil.framework.model.factory.MultiVariantPageObjectFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ValueMyCarPage extends BasePageObject<ValueMyCarPage> {
    private static final String VALUE_MY_CAR = "Value my car";

    @FindBy(css = "input[data-test-id='vrm']")
    private WebElement enterRegInput;

    @FindBy(css = "form[action='/valuation-estimate/'] button[type='submit']")
    private WebElement submitButton;

    @FindBy(css = "div[data-test-id='banner-wrapper-with-blocker'] button")
    WebElement cookieAcceptButton;

    /**
     * Opens given URL, waits for the page to load and creates an instance of itself
     * @param url page URL
     * @return an instance of itself
     */
    public static ValueMyCarPage open(String url) {
        getBrowser().open(url);
        return waitForPage();
    }

    /**
     * Generates an instance of correct ValueMyCarPage page variant
     * @return an instance of correct ValueMyCarPage page variant
     */
    public static ValueMyCarPage waitForPage() {
        return new MultiVariantPageObjectFactory().create(ValueMyCarPage.class);
    }

    /**
     * Submits car registration details
     * @param regNumber car registration number
     * @return itself
     */
    public ValueMyCarPage searchForCarDetails(String regNumber) {
        acceptCookies();
        getBrowser().getTextField(enterRegInput).type(regNumber);
        getBrowser().getButton(submitButton).waitForEnabled().and().click();
        return this;
    }

    private void acceptCookies() {
        if (getBrowser().getButton(cookieAcceptButton).isDisplayed()) {
            getBrowser().getButton(cookieAcceptButton).click().and().waitForNotDisplayed();
        }
    }

    @Override
    public boolean applies() {
        getBrowser().waitForPageToLoad();
        getBrowser().getTextField(enterRegInput).waitForEnabled();
        return getDriver().getPageSource().contains(VALUE_MY_CAR);
    }
}
