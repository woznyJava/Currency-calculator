package com.example.egzaminrest.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Stats {
    private Double max = 0.0;
    private int numberOfInquiries = 0;
    private String theMostPopularForm = "PLN";
    Map<String, Integer> forms = new HashMap<String, Integer>();


    public void updateMax(Double max) {
        this.max = Math.max(this.max, max);
    }

    public void updateNumber() {
        this.numberOfInquiries++;
    }

    public void updateForm(String to) {
        if (!this.forms.containsKey(to)) {
            this.forms.put(to, 1);
        } else {
            this.forms.put(to, this.forms.get(to) + 1);
        }

        String form = "";
        Integer counter = 0;

        for (var key : this.forms.keySet()) {
            if (this.forms.get(key) > counter) {
                form = key;
                counter = this.forms.get(key);
            }
        }

        this.theMostPopularForm = form;
    }

}
