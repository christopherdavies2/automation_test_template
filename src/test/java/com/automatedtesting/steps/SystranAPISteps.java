package com.automatedtesting.steps;

import com.automatedtesting.support.JSONSupport;
import com.automatedtesting.support.ResponseSupport;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

public class SystranAPISteps implements En {

    private static final String X_RAPID_API_HOST = "X-RapidAPI-Host";
    private static final String X_RAPID_API_KEY = "X-RapidAPI-Key";
    private static final String TRANSLATION_TEXT_TRANSLATE = "/translation/text/translate";

    private RequestSpecification request;
    private JSONSupport jsonSupport = new JSONSupport();

    @Value("${systran.base.uri}")
    private String baseUri;

    @Value("${systran.header.x.rapidapi.host}")
    private String rapidAPIHost;

    @Value("${systran.header.x.rapidapi.key}")
    private String rapidAPIKey;

    @Autowired
    private ResponseSupport responseSupport;

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

            responseSupport.setResponse(request.when().get());
        });
    }
}