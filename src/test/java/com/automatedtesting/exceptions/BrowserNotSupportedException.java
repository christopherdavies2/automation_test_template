package com.automatedtesting.exceptions;

public class BrowserNotSupportedException extends Exception {
    public BrowserNotSupportedException(String browser) {
        super(String.format("Browser %s not supported.", browser));
    }
}
