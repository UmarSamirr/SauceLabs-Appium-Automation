package com.saucelabs.steps;

import com.saucelabs.config.ConfigReader;
import com.saucelabs.pages.LoginPage;
import com.saucelabs.pages.ProductsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {

    private final LoginPage loginPage = new LoginPage();
    private final ProductsPage productsPage = new ProductsPage();

    @Given("the app is launched on the login page")
    public void theAppIsLaunchedOnTheLoginPage() {
        assertThat(loginPage.isLoginPageDisplayed())
                .as("Login page should be displayed after app launch")
                .isTrue();
    }

    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("I enter password {string}")
    public void iEnterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("I tap the Login button")
    public void iTapTheLoginButton() {
        loginPage.tapLoginButton();
    }

    @When("I login with valid credentials")
    public void iLoginWithValidCredentials() {
        loginPage.loginWith(
                ConfigReader.getValidUsername(),
                ConfigReader.getValidPassword()
        );
    }

    @Then("I should see the Products page with title {string}")
    public void iShouldSeeTheProductsPageWithTitle(String expectedTitle) {
        assertThat(productsPage.isProductsPageDisplayed())
                .as("Products page should be displayed after login")
                .isTrue();
        assertThat(productsPage.getPageTitle())
                .as("Products page title should match")
                .isEqualTo(expectedTitle);
    }

    @Then("I should see an error message containing {string}")
    public void iShouldSeeAnErrorMessageContaining(String expectedMessage) {
        assertThat(loginPage.isErrorMessageDisplayed())
                .as("Error message should be displayed")
                .isTrue();
        assertThat(loginPage.getErrorMessageText())
                .as("Error message text should contain expected text")
                .contains(expectedMessage);
    }
}
