Feature: Get user by Id - check response structure and content

  Background:
    * url 'https://petstore.swagger.io'

  Scenario: Update pet with PUT request
    Given path '/v2/pet'
    And header Accept = 'application/json'
    And header Content-Type = 'application/json'
    And request
    """
  {
      "id": 33,
             "category": {
               "id": 333,
               "name": "Lizzy"
             },
             "name": "cats",
             "photoUrls": [
               "string"
             ],
             "tags": [
               {
                 "id": 999998877,
                 "name": "cats"
               }
             ],
             "status": "pending"
           }
    """
    When method PUT
    Then status 200
    And match response.id == 33
    And match response.category.id == 333
    And match response.category.name == 'Lizzy'
    And match response.name == 'cats'
    And match response.photoUrls[0] == 'string'
    And match response.tags[0].id == 999998877
    And match response.tags[0].name == 'cats'
    And match response.status == 'pending'

  Scenario: OPTIMIZED. Update pet with PUT request
    # Определили переменную, чтоб призвать реквест из технического класса, расположенного в data
    * def PetUpdateData = Java.type('data.PetRequestData$PetUpdateData')
    * def order = PetUpdateData.getUpdatingPetRequest()

    # Определили переменную, чтоб призвать респонс из технического класса, расположенного в data
    * def PetResponseData = Java.type('data.PetResponseData')
    * def PetUpdateResponseData = Java.type('data.PetResponseData$PetUpdateResponseData')
    * def expected = PetUpdateResponseData.getUpdatingPetResponseData()

    Given path '/v2/pet'
    And header Accept = 'application/json'
    And header Content-Type = 'application/json'
    #Использовали переменную чтоб заполнить поля тестовыми данными из тех класса с реквестом
    And request order
    When method PUT
    Then status 200

    # сравнили пришедшие поля на соответствие значениям, заявленным в тех. классе, где респонс
    And match response == expected