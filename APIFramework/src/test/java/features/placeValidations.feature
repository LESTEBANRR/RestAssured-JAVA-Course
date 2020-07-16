Feature: Validating Place API's

  Scenario: Verify if place is being succesfully added using AddPlace API
    Given AddPlace Payload
    When User calls AddPalce API with POST HTTP request
    Then The API call is success with status code 200
    And Status in response body is OK
