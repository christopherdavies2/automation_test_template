package com.automatedtesting.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@runner1",
        features = "src/test/resources/features",
        glue = {"com.automatedtesting.steps"},
        snippets = SnippetType.CAMELCASE,
        plugin = {"pretty", "html:target/cucumber-reports/html"})
public class Runner1 {
}