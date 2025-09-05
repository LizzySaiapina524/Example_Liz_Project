Feature: Delete Pet by Id - check response structure and content

  Background:
    * url 'https://petstore.swagger.io'

  @only
  Scenario: Add a new pet using POST for checking DELETE method
    # Определили переменную, чтоб призвать реквест из технического класса, расположенного в data
    * def OrderForPetRequest = Java.type('data.PetRequestData$PetUpdateData$OrderForPetRequest')
    * def order = OrderForPetRequest.getOrderRequest()

    # Определили переменную, чтоб призвать респонс из технического класса, расположенного в data
    * def OrderResponseData = Java.type('data.OrderResponseData')
    * def expected = OrderResponseData.getOrderResponseData()

    Given path '/v2/pet'
    And header Accept = 'application/json'
    And header Content-Type = 'application/json'

    #Использовали переменную чтоб заполнить поля тестовыми данными из тех класса с реквестом
    And request order
    When method POST
    Then status 200

  Scenario: Delete new pet
    Given path '/v2/pet/23'
    When method DELETE
    Then status 200
    And match response.code == 200
    And match response.type == 'unknown'
    And match response.message == '23'

    #особенность публичного Petstore API. Не полностью удаляет. Тем не менее проверим респонс на соответствие схеме
  Scenario: Validate response for pet by ID
    Given path '/v2/pet/23'
    When method GET
    Then status 200
    And match response.id == 23
    And match response.photoUrls == []
    And match response.status == "available"

