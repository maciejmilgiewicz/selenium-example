package com.macmil.cucumber.cardetails;

import lombok.Builder;
import lombok.Data;

/**
 * POJO for storing car details found online
 */
@Data
@Builder
public class CarDetails {
    private String registrationNumber;
    private String makeModel;
    private String variantReg;
    private String yearBody;
    private String error;
}
