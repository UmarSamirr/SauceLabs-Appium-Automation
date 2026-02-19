package com.saucelabs.steps;

import com.saucelabs.config.ConfigReader;
import com.saucelabs.pages.CartPage;
import com.saucelabs.pages.CheckoutCompletePage;
import com.saucelabs.pages.CheckoutInfoPage;
import com.saucelabs.pages.CheckoutOverviewPage;
import com.saucelabs.pages.ProductDetailPage;
import com.saucelabs.pages.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseSteps {

    private final ProductsPage productsPage = new ProductsPage();
    private final ProductDetailPage productDetailPage = new ProductDetailPage();
    private final CartPage cartPage = new CartPage();
    private final CheckoutInfoPage checkoutInfoPage = new CheckoutInfoPage();
    private final CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();
    private final CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();

    @Then("the product catalog should be displayed")
    public void theProductCatalogShouldBeDisplayed() {
        assertThat(productsPage.isProductsPageDisplayed())
                .as("Product catalog should be visible")
                .isTrue();
        assertThat(productsPage.getProductCount())
                .as("Product catalog should contain items")
                .isGreaterThan(0);
    }

    @When("I tap on {string}")
    public void iTapOn(String productName) {
        productsPage.tapProductByName(productName);
    }

    @And("I tap Add to Cart")
    public void iTapAddToCart() {
        productDetailPage.tapAddToCart();
    }

    @Then("the Remove button should be visible")
    public void theRemoveButtonShouldBeVisible() {
        assertThat(productDetailPage.isRemoveButtonDisplayed())
                .as("Remove button should appear after adding to cart")
                .isTrue();
    }

    @And("I go back to products page")
    public void iGoBackToProductsPage() {
        productDetailPage.tapBackToProducts();
    }

    @When("I tap the Cart icon")
    public void iTapTheCartIcon() {
        productsPage.tapCartIcon();
    }

    @Then("the cart should contain {int} products")
    public void theCartShouldContainProducts(int expectedCount) {
        assertThat(cartPage.isCartPageDisplayed())
                .as("Cart page should be displayed")
                .isTrue();
        assertThat(cartPage.getCartItemCount())
                .as("Cart should contain expected number of products")
                .isEqualTo(expectedCount);
    }

    @And("the cart should contain {string}")
    public void theCartShouldContain(String productName) {
        List<String> itemNames = cartPage.getCartItemNames();
        assertThat(itemNames)
                .as("Cart should contain product: " + productName)
                .contains(productName);
    }

    @When("I tap Checkout")
    public void iTapCheckout() {
        cartPage.tapCheckout();
    }

    @And("I fill in checkout information")
    public void iFillInCheckoutInformation() {
        assertThat(checkoutInfoPage.isCheckoutInfoPageDisplayed())
                .as("Checkout information page should be displayed")
                .isTrue();
        checkoutInfoPage.fillCheckoutInfo(
                ConfigReader.getCheckoutFirstName(),
                ConfigReader.getCheckoutLastName(),
                ConfigReader.getCheckoutZipCode()
        );
    }

    @And("I tap Continue")
    public void iTapContinue() {
        checkoutInfoPage.tapContinue();
    }

    @Then("the total should equal item total plus tax")
    public void theTotalShouldEqualItemTotalPlusTax() {
        assertThat(checkoutOverviewPage.isOverviewPageDisplayed())
                .as("Checkout overview page should be displayed")
                .isTrue();

        double itemTotal = checkoutOverviewPage.getItemTotal();
        double tax = checkoutOverviewPage.getTax();
        double total = checkoutOverviewPage.getTotal();
        double expectedTotal = Math.round((itemTotal + tax) * 100.0) / 100.0;

        assertThat(total)
                .as("Total ($%.2f) should equal item total ($%.2f) + tax ($%.2f) = $%.2f",
                        total, itemTotal, tax, expectedTotal)
                .isCloseTo(expectedTotal, org.assertj.core.data.Offset.offset(0.01));
    }

    @When("I tap Finish")
    public void iTapFinish() {
        checkoutOverviewPage.tapFinish();
    }

    @Then("I should see {string} message")
    public void iShouldSeeMessage(String expectedMessage) {
        assertThat(checkoutCompletePage.isCompletePageDisplayed())
                .as("Checkout complete page should be displayed")
                .isTrue();
        assertThat(checkoutCompletePage.getThankYouMessage())
                .as("Thank you message should match")
                .contains(expectedMessage);
    }
}
