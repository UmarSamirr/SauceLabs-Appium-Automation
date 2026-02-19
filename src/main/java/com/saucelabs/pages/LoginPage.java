package com.saucelabs.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage extends BasePage {

    private final By usernameField = AppiumBy.accessibilityId("test-Username");
    private final By passwordField = AppiumBy.accessibilityId("test-Password");
    private final By loginButton = AppiumBy.accessibilityId("test-LOGIN");
    private final By errorMessageContainer = AppiumBy.accessibilityId("test-Error message");

    public LoginPage() {
        super();
    }

    public void enterUsername(String username) {
        sendKeys(usernameField, username);
    }

    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }

    public void tapLoginButton() {
        scrollToElement("LOGIN");
        click(loginButton);
    }

    public void loginWith(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        tapLoginButton();
    }

    public boolean isLoginPageDisplayed() {
        return isDisplayed(loginButton);
    }

    public String getErrorMessageText() {
        try {
            WebElement container = findElement(errorMessageContainer);
            List<WebElement> textViews = container.findElements(By.className("android.widget.TextView"));
            StringBuilder sb = new StringBuilder();
            for (WebElement tv : textViews) {
                sb.append(tv.getText());
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessageContainer);
    }
}