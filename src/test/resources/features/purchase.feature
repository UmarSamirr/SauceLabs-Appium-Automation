@purchase @e2e
Feature: End-to-end purchase flow in SauceLabs Demo App

  Background:
    Given the app is launched on the login page
    When I login with valid credentials
    Then I should see the Products page with title "PRODUCTS"

  Scenario: Complete purchase of two products
    # Validate product catalog is displayed
    Then the product catalog should be displayed

    # Add first product to cart
    When I tap on "Sauce Labs Backpack"
    And I tap Add to Cart
    Then the Remove button should be visible
    And I go back to products page

    # Add second product to cart
    When I tap on "Sauce Labs Bike Light"
    And I tap Add to Cart
    Then the Remove button should be visible

    # Navigate to cart and validate items
    When I tap the Cart icon
    Then the cart should contain 2 products
    And the cart should contain "Sauce Labs Backpack"
    And the cart should contain "Sauce Labs Bike Light"

    # Proceed to checkout
    When I tap Checkout
    And I fill in checkout information
    And I tap Continue

    # Validate pricing on overview
    Then the total should equal item total plus tax

    # Complete order
    When I tap Finish
    Then I should see "THANK YOU FOR YOU ORDER" message
