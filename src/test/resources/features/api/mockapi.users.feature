@wip
Feature: Users

  /GET http://5d3496f35b83cd0014d0a7fc.mockapi.io/apitesting/v1/users

  A mock API that retrieves user information

  Scenario: Get user by ID
    Given I am using the mockapi base URI
    When I call GET /users/1
    Then the response returns a HTTP status code of 200
    # TODO: add schema check
    And the following JSON is in the response body:
      | $.id               | 1                        |
      | $.createdAt        | 2019-03-09T20:15:02.098Z |
      | $.name             | Norbert Wiza             |
      | $.isPartTime       | false                    |
      | $.locationId       | 16                       |
      | $.favLanguages.[0] | java                     |
      | $.favLanguages.[1] | ruby                     |
      | $.favLanguages.[2] | python                   |
      | $.address.street   | 11 A street              |
      | $.address.city     | Leeds                    |
      | $.address.postcode | LS1 1A1                  |