Feature: BestBuy application
  As a user I want to test BestBuy Application

  Scenario Outline: CRUD Test
    When I create a new booking by providing the information firstName "<string>" lastName "<string>" totalPrice "<string>" depositPaid "<string>" bookingDates "<string>" additionalNeeds "<string>"
    Then I verify that the booking with "<name>" is created
    And I update the booking with information firstName "<string>" lastName "<string>" totalPrice "<string>" depositPaid "<string>" bookingDates "<string>" additionalNeeds "<string>"
    And I delete the booking that created with name "<name>"
    Then The booking deleted successfully from the list

    Examples:
      | firstName | lastName | totalPrice | depositPaid | bookingDates                              | additionalNeeds |
      | Prime     | prime1   | 1234       | Yes         | checkin: 2023-01-01, checkout: 2023-02-01 | Breakfast       |
      | Prime2    | prime3   | 5678       | Yes         | checkin: 2023-03-01, checkout: 2023-04-01 | Wi-Fi           |
