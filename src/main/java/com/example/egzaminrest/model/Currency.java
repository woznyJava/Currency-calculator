package com.example.egzaminrest.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Map;
import java.util.TreeMap;

@Data
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String from;
    private String to;
    private Double amount;

    private static Map<Integer, Double> TheBiggestAmount= new TreeMap<>();

    public Currency(Integer id, String from, String to, Double amount) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    //mape zrobic i szukac komenda najwieszkej ammount i po id zwrocic najwieksza. Njaczesniej wyliczna to dodac do liczby int
    //i co uzycie zrobic +1
    //z godzina nwm jak to zrobic
    // z kantorem pomyslec
}
