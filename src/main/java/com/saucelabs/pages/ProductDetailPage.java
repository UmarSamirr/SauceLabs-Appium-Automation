package com.saucelabs.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductDetailPage extends BasePage {

    private final By addToCartButton = AppiumBy.accessibilityId("test-ADD TO CART");
    private final By removeButton = By.xpath(
            "//android.view.ViewGroup[@content-desc='test-REMOVE']");
    private final By backToProductsButton = AppiumBy.accessibilityId("test-BACK TO PRODUCTS");
    private final By productName = By.xpath(
            "//android.widget.TextView[@content-desc='test-Item title']");
    private final By productPrice = By.xpath(
            "//android.widget.TextView[@content-desc='test-Price']");
    private final By productDescription = By.xpath(
            "//android.widget.TextView[@content-desc='test-Item description']");

    public ProductDetailPage() {
        super();
    }

    public void tapAddToCart() {
        com.saucelabs.utils.WaitUtils.waitForPresence(
                AppiumBy.accessibilityId("test-Inventory item page"));
        WebElement addBtn = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().description(\"test-Inventory item page\"))" +
                        ".scrollIntoView(new UiSelector().description(\"test-ADD TO CART\"))"));
        addBtn.click();
    }
    public boolean isRemoveButtonDisplayed() {
        try {
            scrollToElement("REMOVE");
            return isDisplayed(removeButton);
        } catch (Exception e) {
            return false;
        }
    }

    public void tapRemove() {
        click(removeButton);
    }

    public void tapBackToProducts() {
        click(backToProductsButton);
    }

    public String getProductName() {
        return getText(productName);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }

    public String getProductDescription() {
        return getText(productDescription);
    }
}
