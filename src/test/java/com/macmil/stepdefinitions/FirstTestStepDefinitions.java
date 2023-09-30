package com.macmil.stepdefinitions;

import io.cucumber.java.en.Given;

public class FirstTestStepDefinitions {

    @Given("cucumber test works")
    public void cucumberTestWorks() {
        System.out.println("Cucumber test works");
    }
}
