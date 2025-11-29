Feature: UI checks

  Scenario: Verify title and URL
    Given I am on the login page
    When I check the page title contains "Swag Labs"

  Scenario: Verify images
    Given I am on the login page
    Then all images should have valid src attributes
