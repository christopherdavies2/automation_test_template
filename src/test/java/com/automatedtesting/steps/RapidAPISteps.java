package com.automatedtesting.steps;

import com.automatedtesting.support.JSONSupport;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

public class RapidAPISteps implements En {

    private static final String X_RAPID_API_HOST = "X-RapidAPI-Host";
    private static final String X_RAPID_API_KEY = "X-RapidAPI-Key";
    private static final String TRANSLATION_TEXT_TRANSLATE = "/translation/text/translate";

    private RequestSpecification request = RestAssured.given();
    private JSONSupport jsonSupport = new JSONSupport();

    @Value("${rapidapi.base.uri}")
    private String baseUri;

    @Value("${rapidapi.header.x.rapidapi.host}")
    private String rapidAPIHost;

    @Value("${rapidapi.header.x.rapidapi.key}")
    private String rapidAPIKey;

    private Response response;

    public RapidAPISteps() {
        Given("^I am using the language processing base URI$", () -> {
            request.baseUri(baseUri);
        });

        When("^I call GET \\/translation\\/text\\/translate with the parameters:$", (DataTable dataTable) -> {
            request.headers(X_RAPID_API_HOST, rapidAPIHost);
            request.headers(X_RAPID_API_KEY, rapidAPIKey);
            request.basePath(TRANSLATION_TEXT_TRANSLATE);

            Map<String, String> params = dataTable.asMap(String.class, String.class);
            request.params(params);

            response = request.get();
        });

        Then("^the following JSON is in the response body:$", (DataTable dataTable) -> {
            String json = response.getBody().asString();
            Map<String, String> jsonPathAndValues = dataTable.asMap(String.class, String.class);
            jsonSupport.assertEachJsonPathValueIsInJson(json, jsonPathAndValues);
        });
    }
}