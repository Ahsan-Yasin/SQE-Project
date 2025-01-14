@login
Feature: Login feature

  Scenario: Successful login
    Given Navigate to Login Page
    When User enters username "standard_user" and password "secret_sauce"
    And User clicks login
    Then User sees product catalog page
