package com.example.egzaminrest.service;

import com.example.egzaminrest.domain.Result;
import com.example.egzaminrest.model.ExchangeRequest;
import com.example.egzaminrest.model.Stats;
import com.example.egzaminrest.repository.ExchangeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ExchangeServiceTests {

    @Autowired
    private ExchangeService exchangeService;

    @Test
    public void shouldReturnFalse() {
        assert !exchangeService.isExchangeValid("TESTOWA");
        assert !exchangeService.isExchangeValid("FAIL");
        assert !exchangeService.isExchangeValid("VALID");
    }

    @Test
    public void shouldReturnTrue() {
        assert exchangeService.isExchangeValid("PLN");
        assert exchangeService.isExchangeValid("USD");
        assert exchangeService.isExchangeValid("EUR");
    }

    @Test
    public void shouldConvert(){
        String from = "USD";
        String to = "PLN";

        var amount1 = exchangeService.convert(from, to,100.0);
        var amount2 = exchangeService.convert(from, to,100.0);
        var amount3 = exchangeService.convert(from, to,25.0);

        assert amount1.equals(amount2);
        assert !amount1.equals(amount3);
    }

    @Test
    public void shouldGetExchange(){
        ExchangeRequest exchangeRequest = new ExchangeRequest("USD", "PLN", 100.0);
    }
}
