package com.Uber.UberApplicaiton.advice;


import com.Uber.UberApplicaiton.exceptions.ResourceNotFoundException;
import com.Uber.UberApplicaiton.exceptions.RuntimeConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalExceptionalHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> exception(Exception e){
        ApiError apiError = new ApiError( e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> resourceNotFound(Exception e){
        ApiError apiError = new ApiError(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeConflictException.class)
    public ResponseEntity<ApiError> runtimeConflictException(Exception e){
        ApiError  apiError = new ApiError(e.getLocalizedMessage(),HttpStatus.CONFLICT);
        return new ResponseEntity<>(apiError,HttpStatus.CONFLICT);
    }


}
