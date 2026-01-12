Feature: Banking Login

  Scenario: Successful login with valid credentials
  Given user is on Parabank login page
  When user enters username "john"
  And user enters password "demo"
  And user clicks login
  Then user should be logged in successfully

