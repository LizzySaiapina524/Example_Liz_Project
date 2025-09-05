Feature: Get male/female users - check response structure and content

  Background:
    * url 'https://hr-challenge.dev.tapyou.com'

  Scenario: Validate response for gender=male
    Given path 'api/test/users'
    And param gender = 'male'
    When method GET
    Then status 200
    And match response.isSuccess == true
    And match response.errorCode == 0
    And match response.errorMessage == null
    And match response.idList == [10, 15, 33, 94, 501, 911]

  Scenario: Validate response for gender=female
    Given path 'api/test/users'
    And param gender = 'female'
    When method GET
    Then status 200
    And match response.isSuccess == true
    And match response.errorCode == 0
    And match response.errorMessage == null
    And match response.idList == [5,15,16,300,502,503]

  Scenario: Incorrect value for gender
    Given path 'api/test/users'
    And param gender = 'abc'
    When method GET
    Then status 500
    And match response.status == 500
    And match response.error == 'Internal Server Error'
    And match response.message == 'No enum constant com.coolrocket.app.api.test.qa.Gender.abc'
    And match response.path == '/api/test/users'

  Scenario: Empty value for gender
    Given path 'api/test/users'
    And param gender = ''
    When method GET
    Then status 500
    And match response.status == 500
    And match response.error == 'Internal Server Error'
    And match response.message == 'No enum constant com.coolrocket.app.api.test.qa.Gender.'
    And match response.path == '/api/test/users'

  Scenario: Empty parameter gender
    Given path 'api/test/users'
    When method GET
    Then status 400
    And match response.status == 400
    And match response.error == 'Bad Request'
    And match response.message == "Required String parameter 'gender' is not present"
    And match response.path == '/api/test/users'

  Scenario: Gender value in mixed register
    Given path 'api/test/users'
    And param gender = 'FeMale'
    When method GET
    Then status 500
    And match response.status == 500
    And match response.error == 'Internal Server Error'
    And match response.message == 'No enum constant com.coolrocket.app.api.test.qa.Gender.FeMale'
    And match response.path == '/api/test/users'

  Scenario: Gender value with empty space
    Given path 'api/test/users'
    And param gender = ' male'
    When method GET
    Then status 500
    And match response.status == 500
    And match response.error == 'Internal Server Error'
    And match response.message == 'No enum constant com.coolrocket.app.api.test.qa.Gender. male'
    And match response.path == '/api/test/users'