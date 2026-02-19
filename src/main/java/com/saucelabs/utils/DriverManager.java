package com.saucelabs.utils;

import com.saucelabs.config.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public final class DriverManager {

    private static final Logger log = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<AndroidDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
    }

    public static AndroidDriver getDriver() {
        AndroidDriver driver = driverThreadLocal.get();
        if (driver == null) {
            throw new IllegalStateException("Driver not initialized. Call initDriver() first.");
        }
        return driver;
    }

    public static void initDriver() {
        log.info("Initializing AndroidDriver...");

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(ConfigReader.getPlatformName());
        options.setDeviceName(ConfigReader.getDeviceName());
        options.setAutomationName(ConfigReader.getAutomationName());

        File appFile = new File(System.getProperty("user.dir"), ConfigReader.getAppPath());
        options.setApp(appFile.getAbsolutePath());

        options.setNoReset(false);
        options.setFullReset(false);
        options.setAutoGrantPermissions(true);
        options.setNewCommandTimeout(Duration.ofSeconds(300));
        options.setCapability("uiautomator2ServerInstallTimeout", 120000);
        options.setCapability("uiautomator2ServerLaunchTimeout", 120000);
        options.setCapability("adbExecTimeout", 120000);
        options.setCapability("appWaitDuration", 60000);
        options.setCapability("appWaitActivity", "com.swaglabsmobileapp.*");

        try {
            URL appiumUrl = new URL(ConfigReader.getAppiumUrl());
            AndroidDriver driver = new AndroidDriver(appiumUrl, options);
            driver.manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));

            driverThreadLocal.set(driver);
            log.info("AndroidDriver initialized successfully on device: {}",
                    ConfigReader.getDeviceName());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL: " + ConfigReader.getAppiumUrl(), e);
        }
    }

    public static void quitDriver() {
        AndroidDriver driver = driverThreadLocal.get();
        if (driver != null) {
            log.info("Quitting AndroidDriver...");
            driver.quit();
            driverThreadLocal.remove();
            log.info("AndroidDriver quit successfully.");
        }
    }
}
