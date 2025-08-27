Feature: Get user by Id - check response structure and content

  Background:
    * url 'https://petstore.swagger.io'

  Scenario: Add a new pet with POST request
    Given path '/v2/pet'
    And header Accept = 'application/json'
    And header Content-Type = 'application/json'
    And request
    """
  {
      "id": 555023023,
      "category": {
        "id": 345345,
        "name": "Love"
      },
      "name": "Love11",
      "photoUrls": [
        "string"
      ],
      "tags": [
        {
          "id": 25,
          "name": "Love22"
        }
      ],
      "status": "available"
    }
    """
    When method POST
    Then status 200
    And match response.id == 555023023
    And match response.category.id == 345345
    And match response.category.name == 'Love'
    And match response.name == 'Love11'
    And match response.photoUrls[0] == 'string'
    And match response.tags[0].id == 25
    And match response.tags[0].name == 'Love22'
    And match response.status == 'available'

  Scenario: OPTIMIZED. Add a new pet with POST request from Java
    * def PetRequestData = Java.type('data.PetRequestData')
    * def petData = PetRequestData.getPetRequest()

    Given url 'https://petstore.swagger.io'
    And path '/v2/pet'
    And header Accept = 'application/json'
    And header Content-Type = 'application/json'
    And request petData
    When method POST
    Then status 200
    And match response.id == petData.id
    And match response.category.id == petData.category.id
    And match response.name == petData.name
    And match response.tags[0].id == petData.tags[0].id

    Scenario: Place an order for a pet
      Given path '/v2/store/order'
      And header Accept = 'application/json'
      And header Content-Type = 'application/json'
      And request
      """
  {
  "id":23 ,
  "petId": 23,
  "quantity": 1,
  "shipDate": "2025-07-30T23:00:06.189Z",
  "status": "available",
  "complete": true
}
    """
      When method POST
      Then status 200
      And match response.id == 23
      And match response.petId == 23
      And match response.quantity == 1
      And match response.shipDate == '2025-07-30T23:00:06.189+0000'
      And match response.status == 'available'
      And match response.complete == true

  Scenario: OPTIMIZED. Place an order for a pet
    # Определили переменную, чтоб призвать реквест из технического класса, расположенного в data
    * def OrderForPetRequest = Java.type('data.PetRequestData.OrderForPetRequest')
    * def order = OrderForPetRequest.getOrderRequest()

    # Определили переменную, чтоб призвать респонс из технического класса, расположенного в data
    * def PetResponseData = Java.type('data.PetResponseData')
    * def expected = PetResponseData.getPetResponseData()

    Given path '/v2/store/order'
    And header Accept = 'application/json'
    And header Content-Type = 'application/json'
    #Использовали переменную чтоб заполнить поля тестовыми данными из тех класса с реквестом
    And request order
    When method POST
    Then status 200

    # сравнили пришедшие поля на соответствие значениям, заявленным в тех. классе, где респонс
    And match response == expected