Feature: Fund Transfer

  Scenario: Transfer money between accounts
    Given user is logged into Parabank
    When user navigates to Fund Transfer page
    # নোট: আপনার অ্যাকাউন্টে পর্যাপ্ত ব্যালেন্স আছে এমন অ্যামাউন্ট ব্যবহার করুন
    And user transfers amount "500" from account "12345" to account "54321"
    Then transfer should be successful