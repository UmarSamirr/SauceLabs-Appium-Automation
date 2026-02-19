@login @smoke
Feature: Login functionality for SauceLabs Demo App

  Scenario: Successful login with valid credentials
    Given the app is launched on the login page
    When I enter username "standard_user"
    And I enter password "secret_sauce"
    And I tap the Login button
    Then I should see the Products page with title "PRODUCTS"