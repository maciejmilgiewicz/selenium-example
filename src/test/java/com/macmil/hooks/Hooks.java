package com.macmil.hooks;

import com.macmil.framework.webdriver.DriverManager;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Hooks {

    @BeforeAll
    public static void startTestSuite() {
        log.info("Test suite started");
        DriverManager.getInstance().getDriver();
    }

    @AfterAll
    public static void stopTestSuite() {
        log.info("Test suite finished");
        DriverManager.getInstance().destroyDriver();
    }
}
