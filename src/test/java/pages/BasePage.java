package pages;

import org.openqa.selenium.WebDriver;
import support.PropertiesSupport;

public abstract class BasePage {
    WebDriver driver;
    PropertiesSupport props = new PropertiesSupport();
    protected abstract void goToPage();
}
