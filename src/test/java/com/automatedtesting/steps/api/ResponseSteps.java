package com.automatedtesting.steps.api;

import com.automatedtesting.support.JSONSupport;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ResponseSteps extends APIBaseSteps implements En {
    @Value("${json.expected.responses}")
    private String expectedResponsesPath;

    @Value("${json.schemas.path}")
    private String schemasPath;

    private JSONSupport jsonSupport = new JSONSupport();

    public ResponseSteps() {
        Then("^the response returns a HTTP status code of (\\d{3})$", (Integer expStatusCode) -> {
            assertThat(responseSupport.getResponse().statusCode()).isEqualTo(expStatusCode);
        });

        Then("^the response follows the schema specified in \"(.+)\"$", (String schemaFilename) -> {
            jsonSupport.assertJsonMatchesSchema(responseSupport.getResponse(),
                    schemasPath + schemaFilename);
        });

        Then("^the following JSON is in the response body:$", (DataTable dataTable) -> {
            Map<String, String> jsonPathAndValues = dataTable.asMap(String.class, String.class);
            jsonSupport.assertEachJsonPathValueIsInResponse(responseSupport.getResponse(), jsonPathAndValues);
        });

        Then("^the attribute (.+) has a value greater than (\\d+)$", (String jsonPath, Integer expValue) -> {
            jsonSupport.assertJsonPathValueIsGreaterThan(responseSupport.getResponse(), jsonPath, expValue);
        });

        Then("^the response matches the contents of the file specified in \"(.+)\"$", (String filename) -> {
            jsonSupport.assertJsonMatchesExpectedResponseFile(responseSupport.getResponse(),
                    expectedResponsesPath + filename);
        });

        Then("^the attribute (.+) has at least (\\d+) arrays?$", (String jsonPath, Integer expNum) -> {
            jsonSupport.assertJsonPathValueContainsAtLeastXArrays(responseSupport.getResponse(), jsonPath, expNum);
        });
    }

}
