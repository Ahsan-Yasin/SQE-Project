Feature: Product and Cart actions

  Background:
    Given I am on the login page
    Given login data is prepared
    When I login using excel row 1

  Scenario: Open product details page
    Then I should be logged in
    # to keep things simple we'll add product then go to cart

  Scenario: Add first product to cart
    When I add the first product to the cart
    Then the cart should contain 1 item(s)

  Scenario: Add product by name
    When I add product "Sauce Labs Backpack" to the cart
    Then the cart should contain 1 item(s)

  Scenario: Remove product from cart
    When I add the first product to the cart
    Then the cart should contain 1 item(s)
    When I remove all items from the cart
    Then the cart should contain 0 item(s)
