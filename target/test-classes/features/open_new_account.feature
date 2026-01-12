Feature: Open New Account

  Scenario: User opens a new account
    Given user is logged into Parabank
    When user navigates to Open New Account page
    And user selects account type "SAVINGS" and existing account "12345"
    And user clicks Open New Account
    Then new account should be created successfully