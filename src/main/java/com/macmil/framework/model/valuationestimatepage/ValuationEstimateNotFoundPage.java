package com.macmil.framework.model.valuationestimatepage;

import com.macmil.cucumber.cardetails.CarDetails;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ValuationEstimateNotFoundPage extends ValuationEstimatePage {
    private static final String CAR_NOT_FOUND = "We couldn't find a car with that registration";

    @FindBy(css = "div[role='alert'] span")
    private WebElement alertNotFound;

    /**
     * Gathers car details
     * @param registrationNumber car registration number
     * @return car details
     */
    @Override
    public CarDetails gatherCarDetails(String registrationNumber) {
        return CarDetails.builder()
                .registrationNumber(registrationNumber)
                .error(getBrowser().getElement(alertNotFound).getText())
                .build();
    }

    @Override
    public boolean applies() {
        getBrowser().waitForPageToLoad();
        getBrowser().getTextField(alertNotFound).waitForEnabled();
        return getDriver().getPageSource().contains(CAR_NOT_FOUND);
    }
}
