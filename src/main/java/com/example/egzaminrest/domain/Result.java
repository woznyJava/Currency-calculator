package com.example.egzaminrest.domain;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


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
