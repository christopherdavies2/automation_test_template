package com.automatedtesting.support;

import org.springframework.beans.factory.annotation.Value;

public class RequestSupport {
    @Value("${json.request.bodies}")
    private String requestBodiesPath;

    private FileSupport fileSupport = new FileSupport();

    public String getRequestBodyFileContents(String filename) {
        return fileSupport.getFileContents(requestBodiesPath + filename);
    }
}
