package com.macmil;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"pretty", "html:target/cucumber-report.html", "json:target/test-report.json"},
    features = {"src/test/resources/features"}
)
public class RunCucumberTest {}
