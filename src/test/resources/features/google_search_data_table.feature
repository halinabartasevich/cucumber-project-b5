Feature:  passing multiple parameters to the same step

  @google_search_data_table
  Scenario: Searching multiple items
    Given user is on Google page
    Then user searches the following items
      |loop academy|
      |java|
      |selenium|
      |sql|
      |Taras|
      |Halina|
      |Savlat|
      |Polina|
      |Alex|
    And we love Loop Academy