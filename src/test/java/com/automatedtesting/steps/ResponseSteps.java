package com.automatedtesting.steps;

import com.automatedtesting.support.*;
import cucumber.api.java8.*;
import io.cucumber.datatable.*;
import org.springframework.beans.factory.annotation.*;

import java.util.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.assertj.core.api.Java6Assertions.*;

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

        Then("the attribute (.+) has a value greater than (\\d+)", (String jsonPath, Integer expValue) -> {
            jsonSupport.assertJsonPathValueIsGreaterThan(responseSupport.getResponse(), jsonPath, expValue);
        });
    }
}
