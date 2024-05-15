Feature: Hotel account registration
  As a register user,
  I want to add an address,
  so that I can have my addresses saved.

  Scenario: Add valid address
    Given the user is on My account page
    When the user clicks My addresses button
    And the user fills required fields on my address form
    And the user clicks Add a new address button
    Then my addresses page should include new address

