Feature: Logging into Coders Lab edu page
  As a logged-in user,
  I want to open my Prework at Tester Automatyzujący course
  so that I can find the first chapter of my courses.

  Scenario: Happy Path for login
    Given the logged-in user is on the Coders Lab LMS page
    When the user open Tester Automatyzujący course
    And the user clicks pokaż rozdziały button in Prework
    And the user clicks Wstęp do kursu
    Then the page should display first chapter of Prework