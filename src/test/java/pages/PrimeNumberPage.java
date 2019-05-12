package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import support.PropertiesSupport;

public class PrimeNumberPage {
    private static final String BASE_URI = "base_uri";
    private static final String CONFIG = "config.properties";
    private static final String VALUE = "value";

    private PropertiesSupport props = new PropertiesSupport();
    private String baseUri = props.getValueFromFile(CONFIG, BASE_URI);

    @FindBy(name = "number")
    private WebElement enterANumberTextField;

    @FindBy(name = "button")
    private WebElement isItPrimeButton;

    @FindBy(name = "result")
    private WebElement isItPrimeTextField;

    private WebDriver driver;

    public PrimeNumberPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToPage() {
        driver.get(baseUri + "prime-number.htm");
    }

    public void enterANumberTextField(String value) {
        enterANumberTextField.sendKeys(value);
    }

    public String getTextFromEnterANumberTextField() {
        return enterANumberTextField.getAttribute(VALUE);
    }

    public void clickIsItPrimeButton() {
        isItPrimeButton.sendKeys(Keys.RETURN);
    }

    public String getTextFromIsItPrimeTextField() {
        return isItPrimeTextField.getAttribute(VALUE);
    }

}
