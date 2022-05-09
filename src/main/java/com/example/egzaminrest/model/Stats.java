package com.example.egzaminrest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
public class Stats {
    private Double max;
    private int numberOfInquiries;
    private String theMostPopularForm;

}
