package com.example.egzaminrest.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Map;
import java.util.TreeMap;

@Data
public class Result {
    private Double value;
    private String from;
    private String to;

    public Result(String from, String to, Double value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }
}
