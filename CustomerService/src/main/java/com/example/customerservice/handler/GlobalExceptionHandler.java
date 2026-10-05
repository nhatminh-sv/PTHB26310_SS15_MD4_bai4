package com.example.customerservice.handler;

import com.example.customerservice.dto.ApiResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponseError handleValidation(
            MethodArgumentNotValidException ex) {

        return new ApiResponseError(
                LocalDateTime.now(),
                400,
                "Bad Request",
                ex.getBindingResult()
                        .getFieldError()
                        .getDefaultMessage()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponseError handleException(Exception ex) {

        return new ApiResponseError(
                LocalDateTime.now(),
                500,
                "Internal Server Error",
                ex.getMessage()
        );
    }
}