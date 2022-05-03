package com.example.egzaminrest.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyDTO {
    private int id;
    private double ammount;

    private static CurrencyDTO theLargestAmount(Currency currency){
return CurrencyDTO.builder()
        .id(currency.getId())
        .ammount(currency.getAmount())
        .build();
    }
}
