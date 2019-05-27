package com.automatedtesting.support;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class BrowserSupport {
    private static final Logger LOG = Logger.getLogger(BrowserSupport.class);

    private static final String OS_NAME = "os.name";
    private static final String MAC = "mac";
    private static final String WINDOWS = "windows";
    private static final String DISABLE_INFOBARS = "disable-infobars";
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String EDGE = "edge";
    private static final String BROWSER_NOT_SUPPORTED_MSG = "Browser %s is not supported.";
    private static final String OS_NOT_SUPPORTED_MSG = "OS %s is not supported.";

    @Value("${webdriver.chrome}")
    private String webdriverChrome;

    @Value("${webdriver.chrome.filename}")
    private String webdriverChromeFilename;

    @Value("${webdriver.firefox}")
    private String webdriverFirefox;

    @Value("${webdriver.firefox.filename}")
    private String webdriverFirefoxFilename;

    @Value("${webdriver.edge}")
    private String webdriverEdge;

    @Value("${webdriver.edge.filename}")
    private String webdriverEdgeFilename;

    @Value("${webdriver.base.path}")
    private String webdriverBasePath;

    @Value("${browser.headless}")
    private boolean isHeadless;

    @Value("${browser.name}")
    private String browserName;

    @Bean
    public WebDriver getWebdriver() {
        WebDriver webdriver = null;

        try {
            setWebdriverSystemProperties();
            webdriver = getBrowserWebdriver();
            webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (IllegalStateException ex) {
            LOG.info(ex.getMessage());
        }

        return webdriver;
    }

    private void setWebdriverSystemProperties() throws IllegalStateException {
        // Edge does not require system properties to be set
        if (!browserName.equalsIgnoreCase(EDGE)) {
            System.setProperty(getBrowserWebdriverName(), getWebDriverPath());
        }
    }

    private String getWebDriverPath() {
        String webDriverPath = "";

        try {
            webDriverPath = getOSWebdriverPath() + getBrowserWebdriverFilename();
        } catch (Exception ex) {
            LOG.info(ex.getMessage());
        }

        return webDriverPath;
    }

    private String getOSWebdriverPath() throws IllegalStateException {
        String OS = System.getProperty(OS_NAME).toLowerCase();
        String OSPath;

        if (OS.startsWith(MAC)) {
            OSPath = MAC;
        } else if (OS.startsWith(WINDOWS)) {
            OSPath = WINDOWS;
        } else {
            throw new IllegalStateException(String.format(OS_NOT_SUPPORTED_MSG, OS));
        }

        return String.format("%s%s/", webdriverBasePath, OSPath);
    }

    private String getBrowserWebdriverName() throws IllegalStateException {
        if (browserName.equalsIgnoreCase(CHROME)) {
            return webdriverChrome;
        } else if (browserName.equalsIgnoreCase(FIREFOX)) {
            return webdriverFirefox;
        } else if (browserName.equalsIgnoreCase(EDGE)) {
            return webdriverEdge;
        } else {
            throw new IllegalStateException(String.format(BROWSER_NOT_SUPPORTED_MSG, browserName));
        }
    }

    private WebDriver getBrowserWebdriver() throws IllegalStateException {
        if (browserName.equalsIgnoreCase(CHROME)) {
            return getChromeWebdriver();
        } else if (browserName.equalsIgnoreCase(FIREFOX)) {
            return getFirefoxWebdriver();
        } else if (browserName.equalsIgnoreCase(EDGE)) {
            return getEdgeWebdriver();
        } else {
            throw new IllegalStateException(String.format(BROWSER_NOT_SUPPORTED_MSG, browserName));
        }
    }

    private String getBrowserWebdriverFilename() throws IllegalStateException {
        if (browserName.equalsIgnoreCase(CHROME)) {
            return webdriverChromeFilename;
        } else if (browserName.equalsIgnoreCase(FIREFOX)) {
            return webdriverFirefoxFilename;
        } else if (browserName.equalsIgnoreCase(EDGE)) {
            return webdriverEdgeFilename;
        } else {
            throw new IllegalStateException(String.format(BROWSER_NOT_SUPPORTED_MSG, browserName));
        }
    }

    private WebDriver getChromeWebdriver() {
        return new ChromeDriver(getChromeOptions());
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(DISABLE_INFOBARS);
        options.setHeadless(isHeadless);
        return options;
    }

    private WebDriver getFirefoxWebdriver() {
        return new FirefoxDriver(getFirefoxOptions());
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(DISABLE_INFOBARS);
        options.setHeadless(isHeadless);
        return options;
    }

    // EdgeOptions are limited so haven't been used here.
    private WebDriver getEdgeWebdriver() {
        return new EdgeDriver();
    }

}