@skyscanner
Feature: Live Flight Search Create Session

  The Create Session endpoint for the Live Flight Search API.

  https://rapidapi.com/skyscanner/api/skyscanner-flight-search

  POST /services/pricing/v1.0

  Create a flight search session. A successful response contains no content.
  The session key to poll the results is provided in the Location header of the response.
  The last value of location header contains the session key which is required when polling the session.

  Scenario: User creates a session
    Given I am using the Skyscanner Flight Search API base URI
    When I call POST /services/pricing/v1.0 with the following parameters:
      | country          | US         |
      | currency         | USD        |
      | locale           | en-US      |
      | originPlace      | SFO-sky    |
      | destinationPlace | LHR-sky    |
      | outboundDate     | 2019-09-01 |
      | adults           | 1          |
    Then the response will return a HTTP 201 status
    And the response will return the following headers:
      | cache-control  | private          |
      | content-type   | application/json |
      | server         | RapidAPI-1.0.16  |
      | connection     | close            |
      | content-length | 2                |
    And the response body will be empty