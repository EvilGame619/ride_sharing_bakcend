package com.Uber.UberApplicaiton.exceptions;

public class OtpNotMatchException extends RuntimeException{
    public OtpNotMatchException() {

    }
    public OtpNotMatchException(String message) {
        super(message);
    }
}
