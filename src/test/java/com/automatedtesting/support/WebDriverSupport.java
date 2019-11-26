package com.automatedtesting.support;

import com.automatedtesting.exceptions.BrowserNotSupportedException;
import com.automatedtesting.exceptions.OperatingSystemNotSupportedException;
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
public class WebDriverSupport {
    private static final Logger LOG = Logger.getLogger(WebDriverSupport.class);

    private static final String OS_NAME = "os.name";
    private static final String MAC = "mac";
    private static final String WINDOWS = "windows";
    private static final String DISABLE_INFOBARS = "disable-infobars";
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String EDGE = "edge";

    @Value("${web.driver.chrome}")
    private String webDriverChrome;

    @Value("${web.driver.chrome.filename}")
    private String webDriverChromeFilename;

    @Value("${web.driver.firefox}")
    private String webDriverFirefox;

    @Value("${web.driver.firefox.filename}")
    private String webDriverFirefoxFilename;

    @Value("${web.driver.base.path}")
    private String webDriverBasePath;

    @Value("${browser.headless}")
    private boolean isHeadless;

    @Value("${browser.name}")
    private String browserName;

    @Bean
    public WebDriver getWebDriver() {
        WebDriver webDriver = null;

        try {
            setWebDriverSystemProperties();
            webDriver = getBrowserWebDriver();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            webDriver.manage().window().fullscreen();
        } catch (OperatingSystemNotSupportedException | BrowserNotSupportedException ex) {
            LOG.error(ex.getMessage());
        }

        return webDriver;
    }

    private void setWebDriverSystemProperties()
            throws BrowserNotSupportedException, OperatingSystemNotSupportedException {
        // Edge does not require system properties to be set
        if (!browserName.equalsIgnoreCase(EDGE)) {
            System.setProperty(getBrowserWebDriverName(), getWebDriverPath());
        }
    }

    private String getWebDriverPath() throws OperatingSystemNotSupportedException, BrowserNotSupportedException {
        return webDriverBasePath + getOSWebDriverPath() + getBrowserWebDriverFilename();
    }

    private String getOSWebDriverPath() throws OperatingSystemNotSupportedException {
        String OS = System.getProperty(OS_NAME).toLowerCase();
        String OSPath;

        if (OS.startsWith(MAC)) {
            OSPath = MAC;
        } else if (OS.startsWith(WINDOWS)) {
            OSPath = WINDOWS;
        } else {
            throw new OperatingSystemNotSupportedException(OS);
        }

        return OSPath + "/";
    }

    private String getBrowserWebDriverName() throws BrowserNotSupportedException {
        if (browserName.equalsIgnoreCase(CHROME)) {
            return webDriverChrome;
        } else if (browserName.equalsIgnoreCase(FIREFOX)) {
            return webDriverFirefox;
        } else {
            throw new BrowserNotSupportedException(browserName);
        }
    }

    private WebDriver getBrowserWebDriver() throws BrowserNotSupportedException {
        if (browserName.equalsIgnoreCase(CHROME)) {
            return getChromeWebDriver();
        } else if (browserName.equalsIgnoreCase(FIREFOX)) {
            return getFirefoxWebDriver();
        } else if (browserName.equalsIgnoreCase(EDGE)) {
            return getEdgeWebDriver();
        } else {
            throw new BrowserNotSupportedException(browserName);
        }
    }

    private String getBrowserWebDriverFilename() throws BrowserNotSupportedException {
        if (browserName.equalsIgnoreCase(CHROME)) {
            return webDriverChromeFilename;
        } else if (browserName.equalsIgnoreCase(FIREFOX)) {
            return webDriverFirefoxFilename;
        } else {
            throw new BrowserNotSupportedException(browserName);
        }
    }

    private WebDriver getChromeWebDriver() {
        return new ChromeDriver(getChromeOptions());
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(DISABLE_INFOBARS);
        options.setHeadless(isHeadless);
        return options;
    }

    private WebDriver getFirefoxWebDriver() {
        return new FirefoxDriver(getFirefoxOptions());
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(DISABLE_INFOBARS);
        options.setHeadless(isHeadless);
        return options;
    }

    // EdgeOptions are limited so haven't been used here.
    private WebDriver getEdgeWebDriver() {
        return new EdgeDriver();
    }

}