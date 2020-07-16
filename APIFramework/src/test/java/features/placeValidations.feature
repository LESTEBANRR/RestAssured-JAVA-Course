Feature: Validating Place API's

  Scenario: Verify if place is being succesfully added using AddPlace API
    Given AddPlace Payload
    When User calls "AddPlace" API with POST HTTP request
    Then The API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
