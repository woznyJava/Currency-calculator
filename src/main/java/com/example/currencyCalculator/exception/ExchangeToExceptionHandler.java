package com.example.currencyCalculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExchangeToExceptionHandler {

    @ExceptionHandler(ToException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO handleMailToException(ToException toException) {
        return ExceptionDTO.fromException(toException);
    }
}


