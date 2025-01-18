package com.Uber.UberApplicaiton.advice;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class ApiError {

    private HttpStatus status;
    private String message;

    public ApiError(String error, HttpStatus code) {

        this.message=error;
        this.status = code;

    }

}
