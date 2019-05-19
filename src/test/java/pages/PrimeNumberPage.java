package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PrimeNumberPage extends BasePage {
    private static final String BASE_URI = "base_uri";
    private static final String CONFIG = "common.properties";
    private static final String VALUE = "value";
    private static final String PATH = "prime-number.htm";

    private String baseUri = props.getValueFromFile(CONFIG, BASE_URI);
    private String url = baseUri + PATH;

    @FindBy(name = "number")
    private WebElement enterANumberTextField;

    @FindBy(name = "button")
    private WebElement isItPrimeButton;

    @FindBy(name = "result")
    private WebElement isItPrimeTextField;

    public PrimeNumberPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToPage() {
        driver.get(url);
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
