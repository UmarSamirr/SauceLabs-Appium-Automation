package com.saucelabs.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends BasePage {

    private final By productsTitle = By.xpath(
            "//android.widget.TextView[@text='PRODUCTS']");
    private final By cartBadge = AppiumBy.accessibilityId("test-Cart");
    private final By productItems = By.xpath(
            "//android.view.ViewGroup[@content-desc='test-Item']");
    private final By productNames = By.xpath(
            "//android.widget.TextView[@content-desc='test-Item title']");
    private final By productPrices = By.xpath(
            "//android.widget.TextView[@content-desc='test-Price']");

    public ProductsPage() {
        super();
    }

    public boolean isProductsPageDisplayed() {
        return isDisplayed(productsTitle);
    }

    public String getPageTitle() {
        return getText(productsTitle);
    }

    public void tapProductByName(String productName) {
        scrollToElement(productName);
        By productLocator = By.xpath(
                "//android.widget.TextView[@text='" + productName + "']");
        click(productLocator);
    }

    public void tapCartIcon() {
        click(cartBadge);
    }

    public List<String> getAllProductNames() {
        List<WebElement> elements = findElements(productNames);
        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public int getProductCount() {
        return findElements(productItems).size();
    }
}
