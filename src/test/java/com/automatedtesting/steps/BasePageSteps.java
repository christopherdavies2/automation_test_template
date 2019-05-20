package com.automatedtesting.steps;

import org.openqa.selenium.WebDriver;
import com.automatedtesting.support.DriverSupport;

class BasePageSteps {
    WebDriver driver = new DriverSupport().getDriver();
}
