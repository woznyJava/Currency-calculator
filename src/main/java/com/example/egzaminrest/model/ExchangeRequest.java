package com.example.egzaminrest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ExchangeRequest {
    @NotEmpty
    private String from;
    @NotEmpty
    private String to;
    @NotEmpty
    private Double amount;
}
