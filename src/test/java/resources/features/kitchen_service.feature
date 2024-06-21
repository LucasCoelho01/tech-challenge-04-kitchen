Feature: Kitchen Service

  Scenario: Create a new kitchen
    Given a Kitchen payload with id "123456", customer "Lucas" and totalPrice 15.0
    When the client requests to create a Kitchen
    Then the response should contain the Kitchen's ID and details