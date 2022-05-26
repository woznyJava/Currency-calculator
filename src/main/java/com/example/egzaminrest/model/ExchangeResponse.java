package com.example.egzaminrest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeResponse {
    private String message;
    private Double amount;
    private Boolean success;
    private String externalErrorMessage;
}
