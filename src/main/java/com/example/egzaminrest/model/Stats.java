package com.example.egzaminrest.model;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Stats {
    private Double max = 0.0;
    private int numberOfInquiries = 0;
    private String theMostPopularFrom = "";

    Map<String, Integer> froms = new HashMap<String, Integer>();

    public void updateMax(Double max) {
        this.max = Math.max(this.max, max);
    }

    public void increaseNumber() {
        this.numberOfInquiries++;
    }

    public void updateFroms(String value) {
        if (!this.froms.containsKey(value)) {
            this.froms.put(value, 1);
        } else {
            this.froms.put(value, this.froms.get(value) + 1);
        }

        String form = "";
        Integer counter = 0;

        for (var key : this.froms.keySet()) {
            if (this.froms.get(key) > counter) {
                form = key;
                counter = this.froms.get(key);
            }
        }
        this.theMostPopularFrom = form;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "max=" + max +
                ", numberOfInquiries=" + numberOfInquiries +
                ", theMostPopularForm='" + theMostPopularFrom + '\'' +
                '}';
    }
}
