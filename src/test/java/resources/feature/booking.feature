Feature: Testing different request on the restful booker application

  Scenario: Check if the restful booker application can be accessed by users
    When User sends a GET Booking request to list endpoint
    Then User must get back a valid status code 200

  Scenario Outline: Create a new restful booker & verify if the student is added
    When I create a new booking by providing the information firstName "<firstName>" lastName "<lastName>" totalPrice "<totalPrice>" depositPaid "<depositPaid>" bookingDates "<bookingDates>" additionalNeeds "<additionalNeeds>"
    Then I verify that the booking with "<email>" is created
    Examples:
      | firstName | lastName | totalPrice | depositPaid | bookingDates                              | additionalNeeds |
      | Prime     | prime1   | 1234       | Yes         | checkin: 2023-01-01, checkout: 2023-02-01 | Breakfast       |
      | Prime2    | prime3   | 5678       | Yes         | checkin: 2023-03-01, checkout: 2023-04-01 | Wi-Fi           |





