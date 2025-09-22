Feature: Docuport Login Feature


  Background: this is for navigating Docupirt login page
    Given user is on Docuport login page

  @smoke
  Scenario: Login as a Client
#    Given user is on Docuport login page
    When  user enters username for clien
    And user enters password for client
    And use clicks login
    Then user should be able to see the home page for client


  @smoke
  Scenario: Login as a employee
#    Given user is on Docuport login page
    When  user enters username for employee
    And user enters password for employee
    And use clicks login
    Then user should be able to see the home page for employee


  @smoke
  Scenario: Login as a Advisor
#    Given user is on Docuport login page
    When  user enters username for advisor
    And user enters password for advisor
    And use clicks login
    Then user should be able to see the home page for advisor


  @smoke
  Scenario: Login as a supervisor
#    Given user is on Docuport login page
    When  user enters username for supervisor
    And user enters password for supervisor
    And use clicks login
    Then user should be able to see the home page for supervisor


  @dataTableLogin @smoke
  Scenario: Login as a client map practice
    When user enters credentials
      |username|b1g1_client@gmail.com|
      |password|Group1              |
    Then user should be able to see the home page for client




















