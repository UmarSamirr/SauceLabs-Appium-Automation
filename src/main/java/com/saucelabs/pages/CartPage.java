package com.saucelabs.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private final By yourCartTitle = By.xpath(
            "//android.widget.TextView[@text='YOUR CART']");
    private final By cartItems = By.xpath(
            "//android.view.ViewGroup[@content-desc='test-Item']");
    private final By removeButtons = AppiumBy.accessibilityId("test-REMOVE");

    public CartPage() {
        super();
    }

    public boolean isCartPageDisplayed() {
        return isDisplayed(yourCartTitle);
    }

    public int getCartItemCount() {
        try {
            return driver.findElements(cartItems).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public List<String> getCartItemNames() {
        List<String> names = new ArrayList<>();
        List<WebElement> descriptions = driver.findElements(
                AppiumBy.accessibilityId("test-Description"));
        for (WebElement desc : descriptions) {
            List<WebElement> textViews = desc.findElements(
                    By.className("android.widget.TextView"));
            if (!textViews.isEmpty()) {
                names.add(textViews.get(0).getText());
            }
        }
        return names;
    }

    public List<String> getCartItemPrices() {
        List<String> prices = new ArrayList<>();
        List<WebElement> priceContainers = driver.findElements(
                AppiumBy.accessibilityId("test-Price"));
        for (WebElement container : priceContainers) {
            List<WebElement> textViews = container.findElements(
                    By.className("android.widget.TextView"));
            if (!textViews.isEmpty()) {
                prices.add(textViews.get(0).getText());
            }
        }
        return prices;
    }

    public void tapCheckout() {
        WebElement checkoutBtn = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().description(\"test-Cart Content\"))" +
                        ".scrollIntoView(new UiSelector().description(\"test-CHECKOUT\"))"));
        checkoutBtn.click();
    }

    public void tapContinueShopping() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().description(\"test-Cart Content\"))" +
                        ".scrollIntoView(new UiSelector().description(\"test-CONTINUE SHOPPING\"))")).click();
    }

    public void removeItemByIndex(int index) {
        List<WebElement> buttons = driver.findElements(removeButtons);
        if (index < buttons.size()) {
            buttons.get(index).click();
        }
    }
}