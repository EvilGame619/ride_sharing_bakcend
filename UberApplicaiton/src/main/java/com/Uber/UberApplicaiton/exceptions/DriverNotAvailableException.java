package com.Uber.UberApplicaiton.exceptions;

public class DriverNotAvailableException extends RuntimeException{
    public DriverNotAvailableException() {
    }

    public DriverNotAvailableException(String message) {
        super(message);
    }
}
