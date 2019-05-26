package com.automatedtesting.pages;

import com.automatedtesting.support.BrowserSupport;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PrimeNumberPage {
    private static final String VALUE = "value";
    private static final String PATH = "prime-number.htm";

    @Value("${uri}")
    private String baseUri;

    @FindBy(name = "number")
    private WebElement enterANumberTextField;

    @FindBy(name = "button")
    private WebElement isItPrimeButton;

    @FindBy(name = "result")
    private WebElement isItPrimeTextField;

    private final WebDriver driver;

    @Autowired
    public PrimeNumberPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToPage() {
        driver.get(baseUri + PATH);
    }

    public void enterANumberTextField(String value) {
        enterANumberTextField.sendKeys(value);
    }

    public void clickIsItPrimeButton() {
        isItPrimeButton.sendKeys(Keys.RETURN);
    }

    public String getTextFromIsItPrimeTextField() {
        return isItPrimeTextField.getAttribute(VALUE);
    }

}
