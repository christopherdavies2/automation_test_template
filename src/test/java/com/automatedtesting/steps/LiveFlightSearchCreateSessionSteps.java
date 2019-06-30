package com.automatedtesting.steps;

import cucumber.api.java8.*;
import io.cucumber.datatable.*;
import io.restassured.*;
import io.restassured.config.*;
import io.restassured.http.*;
import io.restassured.response.*;
import io.restassured.specification.*;
import org.apache.log4j.*;
import org.springframework.beans.factory.annotation.*;

import java.util.*;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.*;

public class LiveFlightSearchCreateSessionSteps implements En {

    private static final Logger LOG = Logger.getLogger(LiveFlightSearchCreateSessionSteps.class);

    private static final String X_RAPID_API_HOST = "X-RapidAPI-Host";
    private static final String X_RAPID_API_KEY = "X-RapidAPI-Key";
    private static final String SERVICES_PRICING_V_1_0 = "/apiservices/pricing/v1.0";
    private static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";
    private static final String X_WWW_FORM_URLENCODED = "x-www-form-urlencoded";
    private static final String EMPTY_CURLY_BRACKETS = "{}";

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
            Map<String, String> params = dataTable.asMap(String.class, String.class);

            request.basePath(SERVICES_PRICING_V_1_0)
                   .header(X_RAPID_API_HOST, skyscannerAPIHost)
                   .header(X_RAPID_API_KEY, skyscannerAPIKey)
                   .formParams(params)
                   .config(RestAssured.config()
                                      .encoderConfig(EncoderConfig.encoderConfig()
                                      .encodeContentTypeAs(X_WWW_FORM_URLENCODED, ContentType.URLENC)))
                                      .contentType(APPLICATION_X_WWW_FORM_URLENCODED);

            LOG.info(request.log().everything());
            response = request.post();
        });

        Then("^the response will return a HTTP (\\d{3}) status$", (Integer statusCode) -> {
            LOG.info(response.body().asString());
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
            assertThat(response.body()).isEqualTo(EMPTY_CURLY_BRACKETS);
        });
    }
}
