Feature: Hotel account registration
  As a register user,
  I want to add an address,
  so that I can have my addresses saved.

  Scenario: Add valid address
    Given the user is on the my account page
    When the user clicks the my addresses button
    And the user fills the required fields on the my address form
    And the user clicks the add a new address button
    Then there is displayed a message "Your addresses are listed below."
    And the new address is added to the list

