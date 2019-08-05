package com.automatedtesting.steps.api;

import com.automatedtesting.support.FileSupport;
import com.automatedtesting.support.JSONSupport;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class ResponseSteps extends APIBaseSteps implements En {
    private JSONSupport jsonSupport = new JSONSupport();
    private FileSupport fileSupport = new FileSupport();

    public ResponseSteps() {
        Then("^the response returns a HTTP status code of (\\d{3})$", (Integer expStatusCode) -> {
            assertThat(responseSupport.getResponse().statusCode()).isEqualTo(expStatusCode);
        });

        Then("^the response follows the schema specified in \"(.+)\"$", (String schemaFile) -> {
            responseSupport.getResponse().then().body(matchesJsonSchemaInClasspath(schemasPath + schemaFile));
        });

        Then("^the following JSON is in the response body:$", (DataTable dataTable) -> {
            Map<String, String> jsonPathAndValues = dataTable.asMap(String.class, String.class);
            jsonSupport.assertEachJsonPathValueIsInResponse(responseSupport.getResponse(), jsonPathAndValues);
        });

        Then("^the attribute (.+) has a value greater than (\\d+)$", (String jsonPath, Integer expValue) -> {
            jsonSupport.assertJsonPathValueIsGreaterThan(responseSupport.getResponse(), jsonPath, expValue);
        });

        Then("^the response matches the contents of the file specified in \"(.+)\"$", (String filename) -> {
            String expBody = fileSupport.getFileContents(expectedResponsesPath + filename);
            // prettyPrint() is used here so that the json is formatted
            String actBody = responseSupport.getResponse().body().prettyPrint();
            assertThat(actBody).isEqualTo(expBody);
        });

        Then("^the attribute (.+) has at least (\\d+) arrays?$", (String jsonPath, Integer expNum) -> {
            String json = responseSupport.getResponse().body().asString();
            jsonSupport.assertJsonPathValueContainsAtLeastXArrays(json, jsonPath, expNum);
        });
    }


}
