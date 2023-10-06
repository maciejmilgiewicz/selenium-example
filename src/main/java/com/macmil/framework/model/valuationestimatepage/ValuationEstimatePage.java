package com.macmil.framework.model.valuationestimatepage;

import com.macmil.cucumber.cardetails.CarDetails;
import com.macmil.framework.model.BasePageObject;
import com.macmil.framework.model.factory.MultiVariantPageObjectFactory;

public abstract class ValuationEstimatePage extends BasePageObject<ValuationEstimatePage> {

    /**
     * Opens given URL, waits for the page to load and creates an instance of itself
     * @param url page URL
     * @return an instance of itself
     */
    public static ValuationEstimatePage open(String url) {
        getBrowser().open(url);
        return waitForPage();
    }

    /**
     * Generates an instance of correct ValuationEstimatePage page variant
     * @return an instance of correct ValuationEstimatePage page variant
     */
    public static ValuationEstimatePage waitForPage() {
        return new MultiVariantPageObjectFactory().create(ValuationEstimatePage.class);
    }

    public abstract CarDetails gatherCarDetails(String registrationNumber);
}
