package com.automatedtesting.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class BrowserSupport {
    private static final String OS_NAME = "os.name";
    private static final String MAC = "Mac";
    private static final String WINDOWS = "Windows";
    private static final String DISABLE_INFOBARS = "disable-infobars";

    @Value("${webdriver.chrome}")
    private String webdriverChrome;

    @Value("${driver.path.windows}")
    private String driverPathWindows;

    @Value(("$driver.path.mac"))
    private String driverPathMac;

    @Value("${browser.headless}")
    private boolean isHeadless;

    @Bean
    public WebDriver getDriver() {
        String driverPath = getDriverPath();
        System.setProperty(webdriverChrome, driverPath);
        WebDriver driver = new ChromeDriver(getChromeOptions());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    private String getDriverPath() {
        String driverPath = "";

        if (System.getProperty(OS_NAME).startsWith(MAC)) {
            driverPath = driverPathMac;
        }
        if (System.getProperty(OS_NAME).startsWith(WINDOWS)) {
            driverPath = driverPathWindows;
        }

        return driverPath;
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(DISABLE_INFOBARS);
        options.setHeadless(isHeadless);
        return options;
    }

}