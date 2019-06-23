package com.automatedtesting.support;

import io.restassured.response.Response;

import java.util.Map;

import static com.jayway.jsonassert.JsonAssert.with;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class JSONSupport {
    private static final String IS_BOOLEAN_REGEX = "^true|false$";
    private static final String IS_INTEGER_REGEX = "^-?(0|[1-9]\\d*)$";
    private static final String IS_FLOAT_REGEX = "^\\d*\\.?\\d+$";

    public void assertEachJsonPathValueIsInResponse(Response response, Map<String, String> jsonPathAndExpValues) {
        String json = response.getBody().asString();
        assertThat(json).isNotNull().isNotEmpty();

        for (Map.Entry<String, String> entry : jsonPathAndExpValues.entrySet()) {
            String jsonPath = entry.getKey();
            String expValue = entry.getValue();
            assertJsonPathValueIsInResponse(response, jsonPath, expValue);
        }
    }

    public void assertJsonPathValueIsInResponse(Response response, String jsonPath, String expValue) {
        String json = response.getBody().asString();

        if (expValue.matches(IS_BOOLEAN_REGEX)) {
            with(json).assertThat(jsonPath, is(Boolean.valueOf(expValue)));
        } else if (expValue.matches(IS_INTEGER_REGEX)) {
            with(json).assertThat(jsonPath, is(Integer.parseInt(expValue)));
        } else if (expValue.matches(IS_FLOAT_REGEX)) {
            with(json).assertThat(jsonPath, is(Float.parseFloat(expValue)));
        } else {
            with(json).assertThat(jsonPath, is(expValue));
        }
    }
}