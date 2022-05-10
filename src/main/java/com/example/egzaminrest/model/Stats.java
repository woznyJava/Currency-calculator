package com.example.egzaminrest.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Data
@Getter
@Setter
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

    public void updateForm(String value) {
        if (!this.forms.containsKey(value)) {
            this.forms.put(value, 1);
        } else {
            this.forms.put(value, this.forms.get(value) + 1);
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
