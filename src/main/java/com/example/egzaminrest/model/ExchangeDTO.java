package com.example.egzaminrest.model;

import lombok.Data;

import java.util.Map;

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
