package com.automatedtesting.support;

import io.restassured.response.Response;
import org.springframework.stereotype.Component;

@Component
public class ResponseSupport {
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
