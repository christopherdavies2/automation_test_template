package com.automatedtesting.steps;

import com.automatedtesting.pages.PrimeNumberPage;
import cucumber.api.java8.En;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PrimeNumberPageSteps extends BasePageSteps implements En {
    private PrimeNumberPage primeNumberPage = new PrimeNumberPage(driver);

    public PrimeNumberPageSteps() {

        Given("^I am on the Prime Number page$", () ->
          primeNumberPage.goToPage()
        );

        Given("^I enter the value \"([^\"]*)\"$", (String value) ->
          primeNumberPage.enterANumberTextField(value)
        );


        When("^I click \"Is it prime\\?\"$", () ->
          primeNumberPage.clickIsItPrimeButton()
        );

        Then("^the message \"([^\"]*)\" appears$", (String expectedText) ->
          assertThat(primeNumberPage.getTextFromIsItPrimeTextField(), is(expectedText))
        );
    }
}