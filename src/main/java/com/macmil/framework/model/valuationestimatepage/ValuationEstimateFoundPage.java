package com.macmil.framework.model.valuationestimatepage;

import com.macmil.cucumber.cardetails.CarDetails;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ValuationEstimateFoundPage extends ValuationEstimatePage {
    private static final String YOUR_CAR = "Your car";

    @FindBy(css = "#__next > div:nth-child(2) h1")
    private WebElement makeModel;

    @FindBy(css = "#__next > div:nth-child(2) h2:nth-child(3)")
    private WebElement variantReg;

    @FindBy(css = "#__next > div:nth-child(2) p strong")
    private WebElement yearBody;

    /**
     * Gathers car details
     * @param registrationNumber car registration number
     * @return car details
     */
    @Override
    public CarDetails gatherCarDetails(String registrationNumber) {
        return CarDetails.builder()
                .registrationNumber(registrationNumber)
                .makeModel(getBrowser().getElement(makeModel).getText())
                .variantReg(getBrowser().getElement(variantReg).getText())
                .yearBody(getBrowser().getElement(yearBody).getText())
                .build();
    }

    @Override
    public boolean applies() {
        getBrowser().waitForPageToLoad();
        getBrowser().getTextField(makeModel).waitForEnabled();
        return getDriver().getPageSource().contains(YOUR_CAR);
    }
}
