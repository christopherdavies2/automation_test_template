package com.automatedtesting.support;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@PropertySource("configuration/common.properties")
@PropertySource("configuration/${environment}.properties")
@ComponentScan("com.automatedtesting")
public class ConfigSupport {
}