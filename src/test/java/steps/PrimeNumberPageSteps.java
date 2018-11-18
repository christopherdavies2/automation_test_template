package steps;

import cucumber.api.java8.En;
import pages.PrimeNumberPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PrimeNumberPageSteps implements En {
    private PrimeNumberPage primeNumberPage = new PrimeNumberPage();

    public PrimeNumberPageSteps() {
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
            assertThat(primeNumberPage.getTextFromIsItPrimeTextField(), is(expectedText));
        });
    }
}