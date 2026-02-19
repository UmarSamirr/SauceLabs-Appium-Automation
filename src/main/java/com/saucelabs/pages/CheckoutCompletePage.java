package com.saucelabs.pages;

import org.openqa.selenium.By;

public class CheckoutCompletePage extends BasePage {

    private final By completeTitle = By.xpath(
            "//android.widget.TextView[@text='CHECKOUT: COMPLETE!']");
    private final By thankYouMessage = By.xpath(
            "//android.widget.TextView[contains(@text,'THANK YOU')]");
    private final By orderDispatchedText = By.xpath(
            "//android.widget.TextView[contains(@text,'Your order has been dispatched')]");

    public CheckoutCompletePage() {
        super();
    }

    public boolean isCompletePageDisplayed() {
        return isDisplayed(completeTitle);
    }

    public String getThankYouMessage() {
        return getText(thankYouMessage);
    }

    public boolean isThankYouMessageDisplayed() {
        return isDisplayed(thankYouMessage);
    }

    public boolean isOrderDispatchedTextDisplayed() {
        return isDisplayed(orderDispatchedText);
    }
}
