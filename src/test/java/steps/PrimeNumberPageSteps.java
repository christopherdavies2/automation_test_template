package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.PrimeNumberPage;
import support.RetrySupport;

import java.util.function.BooleanSupplier;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PrimeNumberPageSteps {
    private PrimeNumberPage primeNumberPage = new PrimeNumberPage();
    private RetrySupport rs = new RetrySupport();

    @Given("^I am on the Prime Number page$")
    public void iAmOnThePrimeNumberPage() {
        primeNumberPage.goToPage();
    }

    @Given("^I enter the value \"([^\"]*)\"$")
    public void iEnterTheValue(String value) {
        Runnable action = () -> primeNumberPage.enterANumberTextField(value);
        BooleanSupplier condition = () -> primeNumberPage.getTextFromEnterANumberTextField().length() > 0;
        rs.retryUntilConditionOrTimeoutReached(action, condition, "Getting text from text field.");
    }

    @When("^I click \"Is it prime\\?\"$")
    public void iClickIsItPrime() {
        primeNumberPage.clickIsItPrimeButton();
    }

    @Then("^the message \"([^\"]*)\" appears$")
    public void theMessageAppears(String expectedText) {
        assertThat(primeNumberPage.getTextFromIsItPrimeTextField(), is(expectedText));
    }
}