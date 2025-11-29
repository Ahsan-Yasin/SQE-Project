Feature: Login

  Background:
    Given I am on the login page

  Scenario: Login with valid credentials
    When I login with username "standard_user" and password "secret_sauce"
    Then I should be logged in

  Scenario: Login with invalid credentials
    When I login with username "standard_user" and password "wrong_pass"
    Then I should see a login error message

  Scenario: Login with empty fields
    When I login with username "" and password ""
    Then I should see a login error message

  Scenario: Logout test
    When I login with username "standard_user" and password "secret_sauce"
    Then I should be logged in
    When I logout
    # should be redirected back to login page
