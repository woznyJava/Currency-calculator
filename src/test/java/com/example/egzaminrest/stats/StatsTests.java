package com.example.egzaminrest.stats;

import com.example.egzaminrest.model.Stats;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class StatsTests {
    @Test
    public void shouldUpdateForm(){
        Stats stats = new Stats();
        stats.updateForm("USD");
        stats.updateForm("EUR");
        stats.updateForm("USD");
        stats.updateForm("PLN");
        assert Objects.equals(stats.getTheMostPopularForm(), "USD");
    }
    @Test
    public void shouldUpdateNumber(){
        Stats stats = new Stats();
        stats.updateNumber();
        stats.updateNumber();
        stats.updateNumber();
        assert stats.getNumberOfInquiries() == 3;
    }
    @Test
    public void shouldUpdateMax(){
        Stats stats = new Stats();
        stats.updateMax(300.00);
        stats.updateMax(12000.00);
        stats.updateMax(111.00);
        assert stats.getMax() == 12000.00;
    }


}
