package com.automatedtesting.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = {"com.automatedtesting.steps"},
        snippets = SnippetType.CAMELCASE,
        tags = "@api",
        plugin = {"pretty", "json:target/cucumber-report/runapitests.json"})
public class RunAPITests {
}