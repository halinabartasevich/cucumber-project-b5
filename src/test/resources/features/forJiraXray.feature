@B5G3-108
Feature: demo how to upload json report to xray


  @xray @B5G3-143 @B5G3-146
  Scenario: Login as a client
    Given user is on Docuport login page
    When user enters username for client
    Then user enters password for client
    And user clicks login
    Then user should be able to see the home page for client