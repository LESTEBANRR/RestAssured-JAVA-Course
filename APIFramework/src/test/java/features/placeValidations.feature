Feature: Validating Place API's
@AddPlace
  Scenario Outline: Verify if place is being succesfully added using AddPlace API
    Given AddPlace Payload with <name> <language> and <address>
    When User calls "AddPlace" API with "POST" HTTP request
    Then The API call is success with status code "200"
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify place_Id created maps to "name" = <name> using "getPlace" API

    Examples: 
      | name    | language | address  |
      | AAHome  | English  | World01  |
     #| BBHouse | Spanish  | Sea cost |
@DeletePlace
  Scenario: Verify if delete place functionality is working
    Given DeletePLace payload
    When User calls "deletePlace" API with "POST" HTTP request
    Then The API call is success with status code "200"
    And "status" in response body is "OK"
