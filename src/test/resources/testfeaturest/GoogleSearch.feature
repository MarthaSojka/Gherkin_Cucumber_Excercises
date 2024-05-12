Feature: Google search
  As an anonymous user,
  I want to search key phrases on Google,
  so that I can find the information that I seek.

      Scenario: Happy Path for search
      Given the user is on the Google home page
      When the user types the key phrase "Coderslab" into the search bok
      And the user clicks the search button
      Then the page should display results related to coderslab