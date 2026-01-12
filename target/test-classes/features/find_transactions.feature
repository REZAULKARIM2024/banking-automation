Feature: Find Transactions

  Scenario: Find transactions using date range filter
    Given user is logged into Parabank
    When user navigates to Find Transactions page
    # তারিখ ফরম্যাটটি MM-DD-YYYY অনুযায়ী দিন
    And user enters date range from "01-01-2025" to "12-31-2025"
    And user clicks the Find Transactions button for date range
    Then a table with transaction history should be displayed