package com.Uber.UberApplicaiton.advice;


import com.Uber.UberApplicaiton.exceptions.*;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice

public class GlobalExceptionalHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> exception(Exception e){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> resourceNotFound(Exception e){
        ApiError apiError = new ApiError( HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeConflictException.class)
    public ResponseEntity<ApiError> runtimeConflictException(Exception e){
        ApiError  apiError = new ApiError(HttpStatus.CONFLICT, e.getLocalizedMessage());
        return new ResponseEntity<>(apiError,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DriverNotAvailableException.class)
    public ResponseEntity<ApiError> availabilityException(Exception e){
        ApiError apiError = new ApiError(HttpStatus.PRECONDITION_FAILED, e.getLocalizedMessage());
        return new ResponseEntity<>(apiError,HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(RideRequestStatusException.class)
    public ResponseEntity<ApiError> rideRequestStatus(Exception e){
        ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE, e.getLocalizedMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(OtpNotMatchException.class)
    public ResponseEntity<ApiError> otpNotMatch(Exception e){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PaymentFailedException.class)
    public ResponseEntity<ApiError> paymentFailed(Exception e){
        ApiError apiError = new ApiError(HttpStatus.PAYMENT_REQUIRED, e.getLocalizedMessage());
        return new ResponseEntity<>(apiError, HttpStatus.PAYMENT_REQUIRED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(Exception e){
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, e.getLocalizedMessage());
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> jwtException(Exception e){
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, e.getLocalizedMessage());
        return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDeniedException(Exception e){
        ApiError apiError= new ApiError(HttpStatus.FORBIDDEN, e.getLocalizedMessage());
        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }

}
