Feature: Get user by Id - check response structure and content

  Background:
    * url 'https://petstore.swagger.io'

  Scenario: Add a new order using POST for checking DELETE method
    Given path '/v2/store/order'
    And header Accept = 'application/json'
    And header Content-Type = 'application/json'
    And request
    """
              {
              "id":3 ,
              "petId": 23,
              "quantity": 1,
              "shipDate": "2025-07-30T23:00:06.189Z",
              "status": "available",
              "complete": true
            }
                """
    When method POST
    Then status 200
    And match response.id == 3
    And match response.petId == 23
    And match response.quantity == 1
    And match response.shipDate == '2025-07-30T23:00:06.189+0000'
    And match response.status == 'available'
    And match response.complete == true

  Scenario: Delete new oder
    Given path '/v2/store/order/3'
    When method DELETE
    Then status 200
    And match response.code == 200
    And match response.type == 'unknown'
    And match response.message == '3'

  Scenario: Validate response for pet by ID
    Given path '/v2/store/order/3'
    When method GET
    Then status 404
    And match response.code == 1
    And match response.type == 'error'
    And match response.message == 'Order not found'

