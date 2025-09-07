
@google_search
Feature: Google Search Functionality Title Validation
  User Story: As a user, when I am on a google web search page
  I should be able to search whatever I want and see the relevant result


  Scenario: Search functionality result title validation
    Given user is on Google page
    When user types Loop Academy in the google search box and clicks enter
    Then user should be able to see Loop Academy - Google search in the google title


  Scenario: Search functionality result title validation
    Given user is on Google page
    When user types "Loop Academy" in the google search box and clicks enter
    Then user should be able to see "Loop Academy - Google Search" in the google title


  Scenario: Search functionality result title validation
    Given user is on Google page
    When user types "Nadir Shafiyev" in the google search box and clicks enter
    Then user should be able to see "Nadir Shafiyev - Google Search" in the google title
