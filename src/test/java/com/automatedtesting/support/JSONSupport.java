package com.automatedtesting.support;

import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;

import java.util.Map;

import static com.jayway.jsonassert.JsonAssert.with;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class JSONSupport {

    public void assertEachJsonPathValueIsInResponse(Response response,
                                                    Map<String, String> jsonPathAndExpValues) {
        assertThat(response.getBody()).isNotNull();
        String json = response.getBody().asString();

        for (Map.Entry<String, String> entry : jsonPathAndExpValues.entrySet()) {
            String jsonPath = entry.getKey();
            String expValue = entry.getValue();
            String actValue = JsonPath.read(json, jsonPath).toString();
            assertThat(actValue).isEqualTo(expValue);
        }
    }

    public void assertJsonPathValueIsGreaterThan(Response response, String jsonPath, int expValue) {
        String json = response.getBody().asString();
        with(json).assertThat(jsonPath, greaterThan(expValue));
    }

    public void assertJsonPathValueContainsAtLeastXArrays(String json, String jsonPath, int expNum) {
        JSONArray jsonArray = JsonPath.read(json, jsonPath);
        int actNum = jsonArray.size();
        assertThat(actNum).isGreaterThanOrEqualTo(expNum);
    }

}