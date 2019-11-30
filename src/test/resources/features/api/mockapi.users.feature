@api
Feature: Users

  /GET http://5d3496f35b83cd0014d0a7fc.mockapi.io/apitesting/v1/users

  A mock API that retrieves user information.

  Data is already mocked so no need for build up or clear down.

  Background:
    Given I am using the mockapi base URI

  Scenario: Get user by ID
    When I call GET /users/1
    Then the response returns a HTTP status code of 200
    And the response follows the schema specified in "user_by_id_schema.json"
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

  Scenario: Get all users
    When I call GET /users
    Then the response returns a HTTP status code of 200
    And the response follows the schema specified in "users_schema.json"
    And the attribute $.[*] has at least 1 array
    And the response matches the contents of the file specified in "users_response.json"

  Scenario: Add a user
    When I call POST /users with a request body specified in "user_request_body.json"
    Then the response returns a HTTP status code of 201

  Scenario: Delete a user
    When I call DELETE /users/6
    And the response returns a HTTP status code of 200