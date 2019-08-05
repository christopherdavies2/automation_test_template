package com.automatedtesting.steps.api;

import com.automatedtesting.support.ResponseSupport;
import cucumber.api.java8.En;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class UsersSteps extends APIBaseSteps implements En {

    private static final String USERS = "users/";

    @Value("${mock.api.base.uri}")
    private String baseUri;

    @Autowired
    private ResponseSupport responseSupport;

    public UsersSteps() {
        Given("^I am using the mockapi base URI$", () -> {
            request.baseUri(baseUri);
        });

        When("^I call GET /users\\/(\\d+)$", (Integer id) -> {
            request.basePath(USERS + id);
            responseSupport.setResponse(request.when().get());
        });

        When("I call GET \\/users", () -> {
            request.basePath(USERS);
            responseSupport.setResponse(request.when().get());
        });
    }
}


