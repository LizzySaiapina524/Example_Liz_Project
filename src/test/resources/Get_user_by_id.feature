Feature: Get user by Id - check response structure and content

  Background:
    * url 'https://hr-challenge.dev.tapyou.com'

  Scenario: Validate response for user ID
    Given path 'api/test/user/33'
    When method get
    Then status 200
    And match response.isSuccess == true
    And match response.errorCode == 0
    And match response.errorMessage == null
    And match response.user.id == 33
    And match response.user.name == 'Jesus'
    And match response.user.gender == 'male'
    And match response.user.age == 33
    And match response.user.city == 'Jerusalem'
    And match response.user.registrationDate == '0000-01-01T00:00:00'

  Scenario: Not existed value
    Given path 'api/test/user/99999999999'
    When method get
    Then status 400
    And match response.isSuccess == false
    And match response.errorCode == 400
    And match response.errorMessage == 'NumberFormatException: For input string: \"99999999999\"'
    And match response.user == null

  Scenario: String value instead int
    Given path 'api/test/user/abc'
    When method get
    Then status 400
    And match response.isSuccess == false
    And match response.errorCode == 400
    And match response.errorMessage == 'NumberFormatException: For input string: \"abc\"'
    And match response.user == null

  Scenario: NULL ID
    Given path 'api/test/user/NULL'
    When method get
    Then status 400
    And match response.isSuccess == false
    And match response.errorCode == 400
    And match response.errorMessage == 'NumberFormatException: For input string: \"NULL\"'
    And match response.user == null

  Scenario: Empty ID
    Given path 'api/test/user/'
    When method get
    Then status 404
    And match response.status == 404
    And match response.error == 'Not Found'
    And match response.message == 'No message available'
    And match response.path == '/api/test/user'

  Scenario: Empty space ID
    Given path 'api/test/user/ 33'
    When method get
    Then status 400
    And match response.isSuccess == false
    And match response.errorCode == 400
    And match response.errorMessage == 'NumberFormatException: For input string: \" 33\"'
    And match response.user == null

  Scenario: Negative ID value
    Given path 'api/test/user/-33'
    When method get
    Then status 200
    And match response.isSuccess == true
    And match response.errorCode == 0
    And match response.errorMessage == null
    And match response.user == null

  Scenario: Zero ID value
    Given path 'api/test/user/0'
    When method get
    Then status 500
    And match response.status == 500
    And match response.error == 'Internal Server Error'
    And match response.message == 'No message available'
    And match response.path == '/api/test/user/0'
