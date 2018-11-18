package support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverSupport {
    private static final String DRIVER = "driver";
    private static final String DRIVER_PATH = "driver_path";
    private static final String CONFIG_PROPERTIES = "config.properties";

    private static PropertiesSupport properties = new PropertiesSupport();
    private static String webDriver = properties.getValueFromFile(CONFIG_PROPERTIES, DRIVER);
    private static String webDriverPath = properties.getValueFromFile(CONFIG_PROPERTIES, DRIVER_PATH);
    private static WebDriver driver;

    private DriverSupport() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty(webDriver, webDriverPath);
            driver = new ChromeDriver();
        }

        return driver;
    }

    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            driver.close();
        }
    };

    static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

}