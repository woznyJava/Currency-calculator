package com.example.currencyCalculator.model;

import lombok.Data;

import java.util.*;

@Data
public class ExchangeDTO {
    private boolean success;
    private Long timestamp;
    private String base;
    private String date;
    private Map<String, Double> rates;

    public Double getRate(String name) {
        return this.rates.get(name);
    }
}

