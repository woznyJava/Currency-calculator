package com.example.egzaminrest.domain;

import lombok.*;

@Data
public class Result {
    private String from;
    private String to;
    private Double value;

    public Result(String from, String to, double value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }
}
