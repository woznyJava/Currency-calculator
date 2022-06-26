package com.example.currencyCalculator.exception;

public class UnknownCurrencyTo extends IllegalArgumentException{
    public UnknownCurrencyTo(String s) {
        super(s);
    }
}
