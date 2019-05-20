package com.automatedtesting.support;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("config/application.properties")
@ComponentScan("com.automatedtesting")
@Configuration
@Component
public class Config {
    @Value("${uri}")
    private String uri;

    public String getUri() {
        return uri;
    }
}
