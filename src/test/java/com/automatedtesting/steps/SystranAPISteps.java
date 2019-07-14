package com.automatedtesting.steps;

import com.automatedtesting.support.JSONSupport;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class SystranAPISteps implements En {

    private static final String X_RAPID_API_HOST = "X-RapidAPI-Host";
    private static final String X_RAPID_API_KEY = "X-RapidAPI-Key";
    private static final String TRANSLATION_TEXT_TRANSLATE = "/translation/text/translate";
    private static final String TRANSLATE_SCHEMA_JSON = "schemas/translate-schema.json";

    private RequestSpecification request;
    private JSONSupport jsonSupport = new JSONSupport();

    @Value("${systran.base.uri}")
    private String baseUri;

    @Value("${systran.header.x.rapidapi.host}")
    private String rapidAPIHost;

    @Value("${systran.header.x.rapidapi.key}")
    private String rapidAPIKey;

    private Response response;

    public SystranAPISteps() {
        Given("^I am using the language processing base URI$", () -> {
            request = RestAssured.given().baseUri(baseUri);
        });

        When("^I call GET \\/translation\\/text\\/translate with the parameters:$", (DataTable dataTable) -> {
            request.headers(X_RAPID_API_HOST, rapidAPIHost);
            request.headers(X_RAPID_API_KEY, rapidAPIKey);
            request.basePath(TRANSLATION_TEXT_TRANSLATE);

            Map<String, String> params = dataTable.asMap(String.class, String.class);
            request.params(params);

            response = request.when().get();
        });

        Then("the response returns a HTTP status code of {int}", (Integer expStatusCode) -> {
            assertThat(response.statusCode()).isEqualTo(expStatusCode);
        });

        Then("the response follows the schema specified in {string}", (String schemaFile) -> {
            response.then().body(matchesJsonSchemaInClasspath(TRANSLATE_SCHEMA_JSON));
        });

        Then("^the following JSON is in the response body:$", (DataTable dataTable) -> {
            Map<String, String> jsonPathAndValues = dataTable.asMap(String.class, String.class);
            jsonSupport.assertEachJsonPathValueIsInResponse(response, jsonPathAndValues);
        });
    }
}