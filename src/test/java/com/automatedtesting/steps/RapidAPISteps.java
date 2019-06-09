package com.automatedtesting.steps;

import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

import static com.jayway.jsonassert.JsonAssert.with;
import static org.assertj.core.api.Assertions.*;

public class RapidAPISteps implements En {

    private static final String X_RAPID_API_HOST = "X-RapidAPI-Host";
    private static final String X_RAPID_API_KEY = "X-RapidAPI-Key";
    private static final String TRANSLATION_TEXT_TRANSLATE = "/translation/text/translate";
    private RequestSpecification request = RestAssured.given();

    @Value("${rapidapi.base.uri}")
    private String baseUri;

    @Value("${rapidapi.header.x.rapidapi.host}")
    private String rapidAPIHost;

    @Value("${rapidapi.header.x.rapidapi.key}")
    private String rapidAPIKey;

    private Response response;

    public RapidAPISteps() {
        Given("I am using the language processing base URI", () -> {
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

        Then("^the response returns:$", (DataTable dataTable) -> {
            String json = response.getBody().asString();
            assertThat(json).isNotNull().isNotEmpty();

            Map<String, String> tableAsMap = dataTable.asMap(String.class, String.class);
            for (Map.Entry<String, String> expField : tableAsMap.entrySet()) {
                String jsonPath = expField.getKey();
                String expValue = expField.getValue();
                with(json).assertEquals(jsonPath, expValue);
            }
        });
    }
}