package com.saucelabs.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found on classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    private ConfigReader() {
    }

    public static String get(String key) {
        String systemProp = System.getProperty(key);
        if (systemProp != null) {
            return systemProp;
        }
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config.properties");
        }
        return value;
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static String getAppiumUrl() {
        return get("appium.server.url");
    }

    public static String getPlatformName() {
        return get("platform.name");
    }

    public static String getDeviceName() {
        return get("device.name");
    }

    public static String getAutomationName() {
        return get("automation.name");
    }

    public static String getAppPath() {
        return get("app.path");
    }

    public static int getImplicitWait() {
        return getInt("implicit.wait");
    }

    public static int getExplicitWait() {
        return getInt("explicit.wait");
    }

    public static String getValidUsername() {
        return get("valid.username");
    }

    public static String getValidPassword() {
        return get("valid.password");
    }

    public static String getCheckoutFirstName() {
        return get("checkout.firstname");
    }

    public static String getCheckoutLastName() {
        return get("checkout.lastname");
    }

    public static String getCheckoutZipCode() {
        return get("checkout.zipcode");
    }
}
