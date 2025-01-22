package com.Uber.UberApplicaiton.advice;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ApiError {

    private HttpStatus status;
    private String message;

    public ApiError(HttpStatus code, String error) {

        this.message=error;
        this.status = code;

    }

}
