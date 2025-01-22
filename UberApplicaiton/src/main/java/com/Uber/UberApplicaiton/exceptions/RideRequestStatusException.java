package com.Uber.UberApplicaiton.exceptions;

public class RideRequestStatusException extends RuntimeException{
    public RideRequestStatusException() {
    }

    public RideRequestStatusException(String message) {
        super(message);
    }
}
