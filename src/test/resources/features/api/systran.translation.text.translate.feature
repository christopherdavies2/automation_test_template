@api
Feature: Systran Translation Text Translate

  An endpoint that translates text.
  https://rapidapi.com/systran/api/systran-io-translation-and-nlp?endpoint=568bd090e4b0e203818a59f0

  GET /translation/text/translate?source={source}&target={target}&input={input})
  The parameters source and target use two-letter language codes defined by the ISO 639-1:2002

  Background:
    Given I am using the language processing base URI

  Scenario: Get the French translation for an English word
    When I call GET /translation/text/translate with the parameters:
      | source | en    |
      | target | fr    |
      | input  | hello |
    Then the response returns a HTTP status code of 200
    And the response follows the schema specified in "translate-schema.json"
    And the following JSON is in the response body:
      | $.outputs[0].output              | bonjour |
      | $.outputs[0].stats.nb_characters | 5       |
      | $.outputs[0].stats.nb_tokens     | 1       |
      | $.outputs[0].stats.nb_tus        | 1       |
      | $.outputs[0].stats.nb_tus_failed | 0       |
    And the attribute $.outputs[0].stats.elapsed_time has a value greater than 0
#    this will currently fail due to dynamic elapsed_time
    And the response matches the contents of the file specified in "translate-hello.json"