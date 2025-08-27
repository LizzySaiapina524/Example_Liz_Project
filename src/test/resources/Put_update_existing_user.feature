Feature: Get user by Id - check response structure and content

  Background:
    * url 'https://petstore.swagger.io'

  Scenario: Update user with PUT request
    Given path '/v2/user/cozymo'
    And header Accept = 'application/json'
    And header Content-Type = 'application/json'
    And request
    """
  {
  "id": 222,
  "username": "cozymo",
  "firstName": "Cozy",
  "lastName": "Mo",
  "email": "cozymore@gmail.com",
  "password": "cozymo25",
  "phone": "5555555",
  "userStatus": 1
}
    """
    When method PUT
    Then status 200
    And match response.code == 200
    And match response.type == "unknown"
    And match response.message == "222"

  @only
  Scenario: OPTIMIZED. Update user with PUT request
    # Определили переменную, чтоб призвать реквест из технического класса, расположенного в data
    * def UpdateUserRequest = Java.type('data.UserRequestData$UpdateUserRequest')
    * def user = UpdateUserRequest.getUpdatingUserRequest()

    # Определили переменную, чтоб призвать респонс из технического класса, расположенного в data
    * def UserUpdateResponseData = Java.type('data.UserResponseData$UserUpdateResponseData')
    * def expected = UserUpdateResponseData.getUpdatingUserResponseData()

    Given path '/v2/user/cozymo'
    And header Accept = 'application/json'
    And header Content-Type = 'application/json'

    #Использовали переменную чтоб заполнить поля тестовыми данными из тех класса с реквестом
    And request user
    When method PUT
    Then status 200

    # сравнили пришедшие поля на соответствие значениям, заявленным в тех. классе, где респонс
    And match response == expected