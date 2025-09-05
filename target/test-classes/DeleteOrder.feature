Feature: Delete Order by Id - check response structure and content

  Background:
    * url 'https://petstore.swagger.io'

  Scenario: Add a new order using POST for checking DELETE method
    # Определили переменную, чтоб призвать реквест из технического класса, расположенного в data
    * def OrderRequestData = Java.type('data.OrderRequestData')
    * def order = OrderRequestData.getOrderRequestData()

    # Определили переменную, чтоб призвать респонс из технического класса, расположенного в data
    * def OrderResponseData = Java.type('data.OrderResponseData')
    * def expected = OrderResponseData.getOrderResponseData()

    Given path '/v2/store/order'
    And header Accept = 'application/json'
    And header Content-Type = 'application/json'

    #Использовали переменную чтоб заполнить поля тестовыми данными из тех класса с реквестом
    And request order
    When method POST
    Then status 200

    # сравнили пришедшие поля на соответствие значениям, заявленным в тех. классе, где респонс
    And match response == expected

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

