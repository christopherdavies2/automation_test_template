package com.automatedtesting.support;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@PropertySource("default.properties")
@PropertySource("${environment}.properties")
@ComponentScan("com.automatedtesting")
public class ConfigSupport {
}