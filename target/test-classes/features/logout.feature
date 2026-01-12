Feature: Logout

Scenario: User logs out successfully
  Given user is logged into Parabank
  When user clicks logout
  Then user should be logged out successfully
