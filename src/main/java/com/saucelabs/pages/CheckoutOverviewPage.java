package com.saucelabs.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CheckoutOverviewPage extends BasePage {

    private final By overviewTitle = By.xpath(
            "//android.widget.TextView[@text='CHECKOUT: OVERVIEW']");
    private final By finishButton = AppiumBy.accessibilityId("test-FINISH");
    private final By cancelButton = AppiumBy.accessibilityId("test-CANCEL");
    private final By itemNames = By.xpath(
            "//android.widget.TextView[@content-desc='test-Item title']");
    private final By itemPrices = By.xpath(
            "//android.widget.TextView[@content-desc='test-Price']");
    private final By itemTotalLabel = By.xpath(
            "//android.widget.TextView[contains(@text,'Item total:')]");
    private final By taxLabel = By.xpath(
            "//android.widget.TextView[contains(@text,'Tax:')]");
    private final By totalLabel = By.xpath(
            "//android.widget.TextView[contains(@text,'Total:')]");

    public CheckoutOverviewPage() {
        super();
    }

    public boolean isOverviewPageDisplayed() {
        return isDisplayed(overviewTitle);
    }

    public List<String> getItemNames() {
        List<WebElement> elements = findElements(itemNames);
        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getItemPrices() {
        List<WebElement> elements = findElements(itemPrices);
        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public double getItemTotal() {
        scrollToElement("Item total:");
        String text = getText(itemTotalLabel);
        return parsePrice(text);
    }

    public double getTax() {
        scrollToElement("Tax:");
        String text = getText(taxLabel);
        return parsePrice(text);
    }

    public double getTotal() {
        scrollToElement("Total:");
        String text = getText(totalLabel);
        return parsePrice(text);
    }

    public boolean isTotalCorrect() {
        double itemTotal = getItemTotal();
        double tax = getTax();
        double total = getTotal();
        double expected = Math.round((itemTotal + tax) * 100.0) / 100.0;
        return Math.abs(total - expected) < 0.01;
    }

    public void tapFinish() {
        scrollToElementByAccessibilityId("test-FINISH");
        click(finishButton);
    }

    public void tapCancel() {
        click(cancelButton);
    }

    private double parsePrice(String priceText) {
        String cleaned = priceText.replaceAll("[^0-9.]", "");
        return Double.parseDouble(cleaned);
    }
}
