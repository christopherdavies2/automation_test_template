package com.automatedtesting.steps;

import com.automatedtesting.support.ResponseSupport;
import cucumber.api.java8.En;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.*;

public class UsersSteps implements En {

    private static final String USERS = "users/";

    @Value("${mock.api.base.uri}")
    private String baseUri;

    @Autowired
    private ResponseSupport responseSupport;

    private RequestSpecification request = RestAssured.given().log().all();

    public UsersSteps() {
        Given("^I am using the mockapi base URI$", () -> {
            request.baseUri(baseUri);
        });

        When("^I call GET /users\\/(\\d+)$", (Integer id) -> {
            request.basePath(USERS + id);
            responseSupport.setResponse(request.when().get());
        });
    }
}


