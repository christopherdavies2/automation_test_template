package com.automatedtesting.support;

import java.util.Map;

import static com.jayway.jsonassert.JsonAssert.with;
import static org.assertj.core.api.Assertions.assertThat;

public class JSONSupport {
    private static final String IS_BOOLEAN_REGEX = "^true|false$";
    private static final String IS_INTEGER_REGEX = "^-?(0|[1-9]\\d*)$";
    private static final String IS_FLOAT_REGEX = "^\\d*\\.?\\d+$";

    public void assertEachJsonPathValueIsInJson(String json, Map<String, String> jsonPathAndExpValues) {
        assertThat(json).isNotNull();
        assertThat(json).isNotEmpty();

        for (Map.Entry<String, String> entry : jsonPathAndExpValues.entrySet()) {
            String jsonPath = entry.getKey();
            String expValue = entry.getValue();
            assertJsonPathValueIsInJson(json, jsonPath, expValue);
        }
    }

    public void assertJsonPathValueIsInJson(String json, String jsonPath, String expValue) {
        if (expValue.matches(IS_BOOLEAN_REGEX)) {
            with(json).assertEquals(jsonPath, Boolean.valueOf(expValue));
        } else if (expValue.matches(IS_INTEGER_REGEX)) {
            with(json).assertEquals(jsonPath, Integer.parseInt(expValue));
        } else if (expValue.matches(IS_FLOAT_REGEX)) {
            with(json).assertEquals(jsonPath, Float.parseFloat(expValue));
        } else {
            with(json).assertEquals(jsonPath, expValue);
        }
    }


}