package com.automatedtesting.steps.ui;

import com.automatedtesting.pages.PrimeNumberPage;
import cucumber.api.java8.En;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class PrimeNumberPageSteps implements En {

    @Autowired
    public PrimeNumberPageSteps(PrimeNumberPage primeNumberPage) {
        Given("^I am on the Prime Number page$", () -> {
            primeNumberPage.goToPage();
        });

        Given("^I enter the value \"([^\"]*)\"$", (String value) -> {
            primeNumberPage.enterANumberTextField(value);
        });

        When("^I click \"Is it prime\\?\"$", () -> {
            primeNumberPage.clickIsItPrimeButton();
        });

        Then("^the message \"([^\"]*)\" appears$", (String expectedText) -> {
            assertThat(primeNumberPage.getTextFromIsItPrimeTextField()).isEqualTo(expectedText);
        });
    }
}