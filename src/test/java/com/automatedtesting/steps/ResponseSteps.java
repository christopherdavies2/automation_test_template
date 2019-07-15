package com.automatedtesting.steps;

import com.automatedtesting.support.JSONSupport;
import com.automatedtesting.support.ResponseSupport;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class ResponseSteps implements En {
    private static final String SCHEMAS_PATH = "schemas/";
    private JSONSupport jsonSupport = new JSONSupport();

    @Autowired
    private ResponseSupport responseSupport;

    public ResponseSteps() {
        Then("^the response returns a HTTP status code of (\\d{3})$", (Integer expStatusCode) -> {
            assertThat(responseSupport.getResponse().statusCode()).isEqualTo(expStatusCode);
        });

        Then("^the response follows the schema specified in \"(.+)\"$", (String schemaFile) -> {
            responseSupport.getResponse().then().body(matchesJsonSchemaInClasspath(SCHEMAS_PATH + schemaFile));
        });

        Then("^the following JSON is in the response body:$", (DataTable dataTable) -> {
            Map<String, String> jsonPathAndValues = dataTable.asMap(String.class, String.class);
            jsonSupport.assertEachJsonPathValueIsInResponse(responseSupport.getResponse(), jsonPathAndValues);
        });
    }
}
