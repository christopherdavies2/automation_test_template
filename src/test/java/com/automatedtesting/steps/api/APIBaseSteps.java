package com.automatedtesting.steps.api;

import com.automatedtesting.support.RequestSupport;
import com.automatedtesting.support.ResponseSupport;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;

abstract class APIBaseSteps {

    @Autowired
    ResponseSupport responseSupport;

    RequestSpecification request = RestAssured.given().log().all();
    RequestSupport requestSupport = new RequestSupport();
}
