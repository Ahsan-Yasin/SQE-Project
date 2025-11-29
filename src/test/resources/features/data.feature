Feature: Data driven tests

  Scenario Outline: Login with multiple users (data-driven example)
    Given I am on the login page
    When I login with username "<username>" and password "<password>"
    Then <result>

    Examples:
      | username                 | password      | result                       |
      | standard_user            | secret_sauce  | I should be logged in        |
      | locked_out_user          | secret_sauce  | I should see a login error message |
      | problem_user             | secret_sauce  | I should be logged in        |
      | performance_glitch_user  | secret_sauce  | I should be logged in        |

  Scenario: Read login rows from Excel and use them
    Given I have an Excel file with login data
    When I read login rows from the Excel file
    Then I can use those rows to login
