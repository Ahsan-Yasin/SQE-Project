Feature: Data driven tests

  Scenario: Attempt login for each row in Excel
    Given I am on the login page
    Given login data is prepared
    When I attempt logins from the Excel file
    Then at least one login attempt should succeed

  Scenario: Read login rows from Excel and use them
    Given I have an Excel file with login data
    When I read login rows from the Excel file
    Then I can use those rows to login

  Scenario: Read products from database (example)
    Given a database with products at "jdbc:mysql://localhost:3306/demo" and credentials "root"/"password"
    Then I can read product names from the database

  Scenario: Read products using the configured test DB
    Given a database is configured for tests
    Then I can read product names from the database

  Scenario: Put and get a key in redis (example)
    Given a redis server at "localhost":6379 and a key "test_key" with value "hello"
    Then I should be able to read the value back from redis
