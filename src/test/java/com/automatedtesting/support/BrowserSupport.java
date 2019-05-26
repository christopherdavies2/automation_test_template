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
    private static WebDriver driver;

    @Value("${webdriver.chrome}")
    private String webdriverChrome;

    @Value("${driver.path.windows}")
    private String driverPathWindows;

    @Value("${driver.path")
    private String driverPath;

    // TODO: have config for Mac
    @Bean
    public WebDriver getDriver() {
        System.setProperty(webdriverChrome, driverPathWindows);
        driver = new ChromeDriver(getChromeOptions());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        return options;
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