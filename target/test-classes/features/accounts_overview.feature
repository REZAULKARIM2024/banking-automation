Feature: Accounts Overview

Scenario: Verify accounts overview
  Given user is logged into Parabank
  When user navigates to Accounts Overview page
  Then user should see a list of all accounts with balances
