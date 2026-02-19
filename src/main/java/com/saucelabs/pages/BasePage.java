package com.saucelabs.pages;

import com.saucelabs.utils.DriverManager;
import com.saucelabs.utils.WaitUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class BasePage {

    protected AndroidDriver driver;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
    }

    protected void click(By locator) {
        WaitUtils.waitForClickable(locator).click();
    }

    protected void sendKeys(By locator, String text) {
        WebElement element = WaitUtils.waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return WaitUtils.waitForVisibility(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return WaitUtils.waitForVisibility(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected List<WebElement> findElements(By locator) {
        return WaitUtils.waitForAllVisible(locator);
    }

    protected WebElement findElement(By locator) {
        return WaitUtils.waitForVisibility(locator);
    }

    protected void scrollToElement(String visibleText) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().textContains(\"" + visibleText + "\"))"));
    }

    protected void scrollToElementByAccessibilityId(String accessibilityId) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().description(\"" + accessibilityId + "\"))"));
    }
}
