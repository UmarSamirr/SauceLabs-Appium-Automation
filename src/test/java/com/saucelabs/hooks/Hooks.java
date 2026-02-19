package com.saucelabs.hooks;

import com.saucelabs.utils.DriverManager;
import com.saucelabs.utils.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    private static final Logger log = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) {
        log.info("======================================");
        log.info("Starting scenario: {}", scenario.getName());
        log.info("Tags: {}", scenario.getSourceTagNames());
        log.info("======================================");
        DriverManager.initDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        log.info("Scenario '{}' finished with status: {}", scenario.getName(), scenario.getStatus());

        if (scenario.isFailed()) {
            log.error("Scenario FAILED — capturing screenshot...");
            byte[] screenshot = ScreenshotUtils.takeScreenshot("Failure - " + scenario.getName());
            if (screenshot.length > 0) {
                scenario.attach(screenshot, "image/png", "Failure screenshot");
            }
        }

        DriverManager.quitDriver();
        log.info("Driver quit. Scenario cleanup complete.");
    }
}
