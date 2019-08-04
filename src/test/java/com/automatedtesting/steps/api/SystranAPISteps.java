package com.automatedtesting.steps.api;

import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

public class SystranAPISteps extends APIBaseSteps implements En {

    private static final String X_RAPID_API_HOST = "X-RapidAPI-Host";
    private static final String X_RAPID_API_KEY = "X-RapidAPI-Key";
    private static final String TRANSLATION_TEXT_TRANSLATE = "/translation/text/translate";

    @Value("${systran.base.uri}")
    private String baseUri;

    @Value("${systran.header.x.rapidapi.host}")
    private String rapidAPIHost;

    @Value("${systran.header.x.rapidapi.key}")
    private String rapidAPIKey;

    public SystranAPISteps() {
        Given("^I am using the language processing base URI$", () -> {
            request.baseUri(baseUri);
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