package com.example.egzaminrest.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resultId;
    @Column(name = "_from")
    private String from;
    @Column(name = "_to")
    private String to;
    @Column(name = "_amount")
    private Double amount;

//    public Result(String from, String to, Double ammount) {
//    }
}
