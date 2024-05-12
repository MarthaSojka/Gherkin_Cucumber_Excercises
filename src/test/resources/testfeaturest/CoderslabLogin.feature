Feature: Logging into Coders Lab edu page
  As a register user,
  I want to log into my account at Coders Lab edu page
  so that I can find the information about my active courses.

  Scenario: Happy Path for login
    Given the register user is on the Coders Lab LMS page
    When the user types the login and password
    And the user clicks the login button
    Then the page should display information about my active curses