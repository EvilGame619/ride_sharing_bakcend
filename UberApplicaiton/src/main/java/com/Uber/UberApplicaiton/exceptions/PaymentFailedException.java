package com.Uber.UberApplicaiton.exceptions;

public class PaymentFailedException extends RuntimeException{
    public PaymentFailedException() {
    }

    public PaymentFailedException(String message) {
        super(message);
    }
}
