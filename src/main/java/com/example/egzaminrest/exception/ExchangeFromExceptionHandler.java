package com.example.egzaminrest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExchangeFromExceptionHandler {

    @ExceptionHandler(FromException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO handleMailFromException(FromException fromException) {
        return ExceptionDTO.fromException(fromException);
    }
}
