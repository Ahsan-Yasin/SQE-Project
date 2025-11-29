Feature: Checkout flow

  Background:
    Given I am on the login page
    When I login with username "standard_user" and password "secret_sauce"

  Scenario: Fill checkout form and finish
    When I add the first product to the cart
    Then the cart should contain 1 item(s)
    When I proceed to checkout with first name "John", last name "Doe", postal code "12345"
    Then I finish checkout

  Scenario: Checkout with missing form data shows error
    When I add the first product to the cart
    When I proceed to checkout with first name "", last name "", postal code ""
    Then I should see a checkout error

  Scenario: Successful order placement
    When I add product "Sauce Labs Backpack" to the cart
    When I proceed to checkout with first name "Alice", last name "Smith", postal code "99999"
    Then I finish checkout
