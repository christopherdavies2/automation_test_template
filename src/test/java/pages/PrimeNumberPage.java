package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import support.DriverSupport;
import support.PropertiesSupport;

public class PrimeNumberPage {
    private static final String BASE_URI = "base_uri";
    private static final String PAGE_NAME = "page_name";
    private static final String CONFIG_PROPERTIES = "config.properties";
    private static final String PRIME_NUMBER_PAGE_PROPERTIES = "prime_number_page.properties";
    private static final String ENTER_A_NUMBER_TEXT_FIELD_NAME = "enter_a_number_text_field_name";
    private static final String IS_IT_PRIME_BUTTON_FIELD_NAME = "is_it_prime_button_name";
    private static final String IS_IT_PRIME_TEXT_FIELD_NAME = "is_it_prime_text_field_name";
    private static final String VALUE = "value";

    private PropertiesSupport properties = new PropertiesSupport();
    private String baseUri = properties.getValueFromFile(CONFIG_PROPERTIES, BASE_URI);
    private String page = properties.getValueFromFile(PRIME_NUMBER_PAGE_PROPERTIES, PAGE_NAME);
    private By enterANumberTextField =
            By.name(properties.getValueFromFile(PRIME_NUMBER_PAGE_PROPERTIES, ENTER_A_NUMBER_TEXT_FIELD_NAME));
    private By isItPrimeButton =
            By.name(properties.getValueFromFile(PRIME_NUMBER_PAGE_PROPERTIES, IS_IT_PRIME_BUTTON_FIELD_NAME));
    private By isItPrimeTextField =
            By.name(properties.getValueFromFile(PRIME_NUMBER_PAGE_PROPERTIES, IS_IT_PRIME_TEXT_FIELD_NAME));
    private WebDriver driver = DriverSupport.getDriver();

    public void goToPage() {
        driver.get(baseUri + page);
    }

    public void enterANumberTextField(String value) {
        driver.findElement(enterANumberTextField).sendKeys(value);
    }

    public void clickIsItPrimeButton() {
        driver.findElement(isItPrimeButton).sendKeys(Keys.RETURN);
    }

    public String getTextFromIsItPrimeTextField() {
        return driver.findElement(isItPrimeTextField).getAttribute(VALUE);
    }

}
