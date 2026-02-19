package com.saucelabs.utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ScreenshotUtils {

    private static final Logger log = LoggerFactory.getLogger(ScreenshotUtils.class);

    private ScreenshotUtils() {
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public static byte[] takeScreenshot() {
        try {
            TakesScreenshot driver = (TakesScreenshot) DriverManager.getDriver();
            log.info("Taking screenshot...");
            return driver.getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            log.error("Failed to take screenshot: {}", e.getMessage());
            return new byte[0];
        }
    }

    @Attachment(value = "{name}", type = "image/png")
    public static byte[] takeScreenshot(String name) {
        try {
            TakesScreenshot driver = (TakesScreenshot) DriverManager.getDriver();
            log.info("Taking screenshot: {}", name);
            return driver.getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            log.error("Failed to take screenshot '{}': {}", name, e.getMessage());
            return new byte[0];
        }
    }
}
