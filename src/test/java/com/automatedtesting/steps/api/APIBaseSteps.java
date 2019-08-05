package com.automatedtesting.steps.api;

import com.automatedtesting.support.ResponseSupport;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

abstract class APIBaseSteps {

    @Autowired
    ResponseSupport responseSupport;

    @Value("${json.schemas.path}")
    String schemasPath;

    @Value("${json.expected.responses}")
    String expectedResponsesPath;

    RequestSpecification request = RestAssured.given().log().all();
}
