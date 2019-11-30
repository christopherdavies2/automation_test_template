package com.automatedtesting.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@api3, @api4",
        features = "classpath:features",
        glue = {"com.automatedtesting.steps"},
        snippets = SnippetType.CAMELCASE,
        plugin = {"pretty", "json:target/cucumber-report/runner4.json"})
public class Runner4 {
}