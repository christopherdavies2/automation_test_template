package com.automatedtesting.steps;

import cucumber.api.java8.*;
import io.cucumber.datatable.*;
import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import io.restassured.specification.*;
import org.springframework.beans.factory.annotation.*;
import org.json.JSONObject;

import java.util.*;

import static io.restassured.config.EncoderConfig.*;
import static org.assertj.core.api.Assertions.*;

public class LiveFlightSearchCreateSessionSteps implements En {

    private static final String X_RAPID_API_HOST = "X-RapidAPI-Host";
    private static final String X_RAPID_API_KEY = "X-RapidAPI-Key";
    private static final String SERVICES_PRICING_V_1_0 = "/services/pricing/v1.0";

    private RequestSpecification request = RestAssured.given();
    private Response response;

    @Value("${skyscanner.live.flight.search.base.uri}")
    private String baseUri;

    @Value("${skyscanner.header.x.rapidapi.host}")
    private String skyscannerAPIHost;

    @Value("${skyscanner.header.x.rapidapi.key}")
    private String skyscannerAPIKey;

    public LiveFlightSearchCreateSessionSteps() {
        Given("^I am using the Skyscanner Flight Search API base URI$", () -> {
            request.baseUri(baseUri);
        });

        When("^I call POST \\/services\\/pricing\\/v1.0 with the following parameters:$", (DataTable dataTable) -> {
            request.basePath(SERVICES_PRICING_V_1_0);

            request.header(X_RAPID_API_HOST, skyscannerAPIHost);
            request.header(X_RAPID_API_KEY, skyscannerAPIKey);

            request.config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("application\\/x-www-form-urlencoded", ContentType.URLENC)));
            Map<String, String> params = dataTable.asMap(String.class, String.class);
            request.formParams(params);

            response = request.post();
        });

        Then("^the response will return a HTTP (\\d\\d\\d) status$", (Integer statusCode) -> {
            System.out.println(response.body().asString());
            assertThat(response.statusCode()).isEqualTo(statusCode);
        });

        Then("^the response will return the following headers:$", (DataTable dataTable) -> {
            Map<String, String> expHeaders = dataTable.asMap(String.class, String.class);

            for (String expHeaderKey : expHeaders.keySet()) {
                String expHeaderValue = expHeaders.get(expHeaderKey);
                assertThat(response.getHeader(expHeaderKey)).isEqualTo(expHeaderValue);
            }
        });

        Then("^the response body will be empty$", () -> {
            assertThat(response.body()).isEqualTo("{}");
        });
    }
}
