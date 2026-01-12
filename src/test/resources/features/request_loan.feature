Feature: Request Loan

  Scenario: User requests a loan
    Given user is logged into Parabank
    When user navigates to Request Loan page
    And user enters loan amount "5000" and down payment "500"
    And user clicks Apply Now
    Then loan request should be approved