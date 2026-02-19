package com.saucelabs.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class CheckoutInfoPage extends BasePage {

    private final By checkoutInfoTitle = By.xpath(
            "//android.widget.TextView[@text='CHECKOUT: INFORMATION']");
    private final By firstNameField = AppiumBy.accessibilityId("test-First Name");
    private final By lastNameField = AppiumBy.accessibilityId("test-Last Name");
    private final By zipCodeField = AppiumBy.accessibilityId("test-Zip/Postal Code");
    private final By continueButton = AppiumBy.accessibilityId("test-CONTINUE");
    private final By cancelButton = AppiumBy.accessibilityId("test-CANCEL");
    private final By errorMessage = AppiumBy.accessibilityId("test-Error message");

    public CheckoutInfoPage() {
        super();
    }

    public boolean isCheckoutInfoPageDisplayed() {
        return isDisplayed(checkoutInfoTitle);
    }

    public void enterFirstName(String firstName) {
        sendKeys(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        sendKeys(lastNameField, lastName);
    }

    public void enterZipCode(String zipCode) {
        sendKeys(zipCodeField, zipCode);
    }

    public void fillCheckoutInfo(String firstName, String lastName, String zipCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterZipCode(zipCode);
    }

    public void tapContinue() {
        click(continueButton);
    }

    public void tapCancel() {
        click(cancelButton);
    }

    public String getErrorMessageText() {
        return getText(errorMessage);
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }
}
