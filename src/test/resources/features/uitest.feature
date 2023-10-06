@CheckCarDetails
Feature: Check car details

  Scenario: Check car details
    Given I obtain registration numbers
    When I search for cars details
    Then All car details are valid
