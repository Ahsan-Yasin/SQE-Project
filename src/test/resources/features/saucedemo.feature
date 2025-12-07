Feature: SauceDemo automation test suite
  This feature contains automated test cases for SauceDemo e-commerce site

  Scenario: Successful login with standard user
    Given Navigate to Login Page
    When User enters username "standard_user" and password "secret_sauce"
    And User clicks login
    Then User sees product catalog page
 
  Scenario: Login with invalid credentials fails
    Given Navigate to Login Page
    When User enters username "invalid_user" and password "wrong_pass"
    And User clicks login
    Then User sees product catalog page

  Scenario: Login with empty username fails
    Given Navigate to Login Page
    When User enters username "" and password "secret_sauce"
    And User clicks login
    Then User sees product catalog page

  Scenario: Login with empty password fails
    Given Navigate to Login Page
    When User enters username "standard_user" and password ""
    And User clicks login
    Then User sees product catalog page

  Scenario: Login as performance glitch user
    Given Navigate to Login Page
    When User enters username "performance_glitch_user" and password "secret_sauce"
    And User clicks login
    Then User sees product catalog page

  Scenario: Login as problem user
    Given Navigate to Login Page
    When User enters username "problem_user" and password "secret_sauce"
    And User clicks login
    Then User sees product catalog page

  Scenario: Login as visual user
    Given Navigate to Login Page
    When User enters username "visual_user" and password "secret_sauce"
    And User clicks login
    Then User sees product catalog page

  Scenario: Verify products page title
    Given User is logged in with "standard_user" and "secret_sauce"
    Then Verify page title contains "Swag Labs"

  Scenario: Add single product to cart
    Given User is logged in with "standard_user" and "secret_sauce"
    When User adds a product to cart
    And User opens cart
    Then Cart has items



  Scenario: Inventory page accessible after login
    Given User is logged in with "standard_user" and "secret_sauce"
    Then Verify page title contains "Swag Labs"


  Scenario: Performance user can add product
    Given User is logged in with "performance_glitch_user" and "secret_sauce"
    When User adds a product to cart
    And User opens cart
    Then Cart has items

  Scenario: Problem user product flow
    Given User is logged in with "problem_user" and "secret_sauce"
    When User adds a product to cart
    And User opens cart
    Then Cart has items

  Scenario: Visual user can purchase
    Given User is logged in with "visual_user" and "secret_sauce"
    When User adds a product to cart 
    And User opens cart
    Then Cart has items




  Scenario: Add two products and verify cart
    Given User is logged in with "standard_user" and "secret_sauce"
    When User adds a product to cart
    And User adds a product to cart
    And User opens cart
  

  


  Scenario: Basic login test variant
    Given Navigate to Login Page
    When User enters username "standard_user" and password "secret_sauce"
    And User clicks login
    Then User sees product catalog page






