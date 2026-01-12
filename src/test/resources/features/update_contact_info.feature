Feature: Update Contact Info

Scenario: Update user contact information
  Given user is logged into Parabank
  When user navigates to Update Contact Info page
  And user updates contact info with:
    | firstName | John   |
    | lastName  | Doe    |
    | address   | 123 St |
    | city      | NY     |
    | state     | NY     |
    | zipCode   | 10001  |
    | phone     | 1234567890 |
  And user clicks Update
  Then contact info should be updated successfully
