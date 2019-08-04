package com.automatedtesting.support;

import io.restassured.response.*;
import org.springframework.stereotype.*;

@Component
public class ResponseSupport {
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
        this.response.then().log().all();
    }
}
