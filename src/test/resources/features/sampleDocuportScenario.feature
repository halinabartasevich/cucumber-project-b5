Feature: Docuport Sample Scenario

  @sampleDocuport
  Scenario: Practice click buttons on different pages as a client
    Given user is on Docuport login page
    When user inserts "b1g1_client@gmail.com" to "username" field on "Login" page
    And user inserts "Group1" to "password" field on "Login" page
    And user clicks "login" button on "Login" page
    And user clicks "continue" button on "Choose account" page
    Then user should be able to see the home page for client
    And user clicks "invitations" button on "Left Navigate" page
    And user clicks "my uploads" button on "Left Navigate" page
    And user clicks "home" button on "Left Navigate" page
    And user clicks "Received Docs" button on "Left Navigate" page
    And user clicks "search" button on "Received Docs" page
    And user inserts "tax documents" to "Document name" field on "Received Docs" page

  #  And user clicks "My uploads" button on "Left Navigate" page
 #  And user clicks "Upload Documents" button on "My uploads" page
   # And user clicks "Upload file" button on "My uploads" page
