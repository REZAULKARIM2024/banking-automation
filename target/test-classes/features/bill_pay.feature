Feature: Bill Payment

  Scenario: Pay bill to a payee successfully
    Given user is logged into Parabank
    When user pays bill with following details
      | payeeName | Electric Company |
      | address   | New York         |
      | city      | New York         |
      | state     | NY               |
      | zipCode   | 10001            |
      | phone     | 9999999999       |
      | account   | 12345            |
      | verifyAcc | 12345            |
      | amount    | 50               |
    Then bill payment should be successful
