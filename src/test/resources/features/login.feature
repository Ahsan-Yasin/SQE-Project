Feature: Login

  Background:
    Given I am on the login page
    Given login data is prepared

  Scenario: Login with valid credentials (from Excel)
    When I login using excel row 1
    Then I should be logged in

  Scenario: Login with invalid credentials (from Excel)
    When I login using excel row 3
    Then I should see a login error message

  Scenario: Login with empty fields (from Excel)
    When I login using excel row 4
    Then I should see a login error message

  Scenario: Logout test
    When I login using excel row 1
    Then I should be logged in
    When I logout
    # should be redirected back to login page
