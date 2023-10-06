package com.macmil.utilities.validation;

import com.macmil.cucumber.cardetails.CarDetails;
import com.macmil.cucumber.cardetails.CsvCarDetails;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CarDetailsValidation {

    /**
     * Performs a single car validation by comparing it against a list of valid car details
     * @param car car details to be validated
     * @param validationDetails a list of valid car details
     * @return true if car matches valid details, false otherwise
     */
    public static boolean isCarValid(CarDetails car, List<CsvCarDetails> validationDetails) {
        boolean matchFound = validationDetails.stream().anyMatch(validCar -> car.getMakeModel() != null
                && car.getVariantReg() != null
                && car.getYearBody() != null
                && car.getMakeModel().replaceAll("\\| ", "").equals(validCar.getMakeModel())
                && car.getVariantReg().replaceAll("\\| ", "").equals(validCar.getVariantReg())
                && car.getYearBody() .replaceAll("\\| ", "").equals(validCar.getYearBody()));
        if (matchFound) {
            log.info("Car {} matched one of valid car details stored.", car.getRegistrationNumber());
        } else {
            log.error("Car {} didn't match any of valid car details stored. Error: {}", car.getRegistrationNumber(), car.getError());
        }
        return matchFound;
    }
}
