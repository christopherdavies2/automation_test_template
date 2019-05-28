package com.automatedtesting.exceptions;

public class OperatingSystemNotSupportedException extends Exception {
    public OperatingSystemNotSupportedException(String OS) {
        super(String.format("Operating System %s not supported.", OS));
    }
}
