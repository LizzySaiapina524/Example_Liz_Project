Feature: Get user by Id - check response structure and content

  Background:
    * url 'https://petstore.swagger.io'

  Scenario: Add a new pet using POST for checking DELETE method
    Given path '/v2/pet'
    And header Accept = 'application/json'
    And header Content-Type = 'application/json'
    And request
    """
            {
              "id": 9999,
              "name": "Scratchy Scratch",
              "status": "available",
              "category": {
                "id": 1,
                "name": "Dogs"
              },
              "photoUrls": ["https://example.com/dog.jpg"],
              "tags": [
                {
                  "id": 1,
                  "name": "friendly"
                }
              ]
            }
            """
    When method POST
    Then status 200
    And match response.id == 9999
    And match response.category.id == 1
    And match response.category.name == 'Dogs'
    And match response.name == 'Scratchy Scratch'
    And match response.photoUrls[0] == 'https://example.com/dog.jpg'
    And match response.tags[0].id == 1
    And match response.tags[0].name == 'friendly'
    And match response.status == 'available'

  Scenario: Delete new pet
    Given path '/v2/pet/9999'
    When method DELETE
    Then status 200
    And match response.code == 200
    And match response.type == 'unknown'
    And match response.message == '9999'

  Scenario: Validate response for pet by ID
    Given path '/v2/pet/9999'
    When method GET
    Then status 404
    And match response.code == 1
    And match response.type == 'error'
    And match response.message == 'Pet not found'

