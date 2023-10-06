package com.macmil.stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.macmil.cucumber.cardetails.CarDetails;
import com.macmil.cucumber.cardetails.CsvCarDetails;
import com.macmil.framework.config.ConfigFactory;
import com.macmil.framework.model.valuationestimatepage.ValuationEstimateFoundPage;
import com.macmil.framework.model.valuemycarpage.ValueMyCarPage;
import com.macmil.utilities.file.FileUtilities;
import com.macmil.utilities.regex.RegexUtilities;
import com.macmil.utilities.validation.CarDetailsValidation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.macmil.utilities.funcinterfaces.FunctionalInterfaceWrapper.throwingFunctionWrapper;

@Slf4j
public class StepDefinitions {

    List<String> registrationNumbers;
    List<CarDetails> carDetails = new ArrayList<>();
    List<CsvCarDetails> csvCarDetails;

    @Given("I obtain registration numbers")
    public void i_obtain_registration_numbers() {
        registrationNumbers = FileUtilities.listFilesInDir("input").stream()
                .map(throwingFunctionWrapper(FileUtilities::readFile))
                .map(text -> RegexUtilities.findTextPatterns(text, "[\\w]{2}[\\d]{2} ?[\\w]{3}"))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        log.info("Following registration numbers were found:");
        registrationNumbers.forEach(log::info);
    }

    @When("I search for cars details")
    public void i_search_for_cars_details() {
        registrationNumbers.forEach(regnum -> {
            ValueMyCarPage.open(ConfigFactory.getConfig().getTestUrl());
            ValueMyCarPage.waitForPage().and().searchForCarDetails(regnum);
            carDetails.add(ValuationEstimateFoundPage.waitForPage().and().gatherCarDetails(regnum));
        });
        log.info("All car details:");
        carDetails.forEach(car -> log.info(car.toString()));
    }

    @Then("All car details are valid")
    public void all_car_details_are_valid() {
        boolean allCarsValid = true;
        csvCarDetails = FileUtilities.listFilesInDir("output").stream()
                .map(throwingFunctionWrapper(FileUtilities::readCarDetailsFromFile))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        log.info("All validation car details:");
        csvCarDetails.forEach(car -> log.info(car.toString()));

        // I could have used carDetails.stream().allMatch(car -> isCarValid(car, csvCarDetails));
        // but it short circuits, i.e. stops on the first failed validation,
        // and I would like to check all cars
        for(CarDetails car : carDetails) {
            allCarsValid &= CarDetailsValidation.isCarValid(car,csvCarDetails);
        }
        assertTrue(allCarsValid, "Some cars did not pass validation!");
    }
}
