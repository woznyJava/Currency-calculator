package com.example.egzaminrest.controller;

import com.example.egzaminrest.model.ExchangeDTO;
import com.example.egzaminrest.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exchange")
public class ExchangeController {

    private final ExchangeService exchangeService;

    @GetMapping("/token")
    public Map<String, String> getToken() {
        return Map.of("token", exchangeService.getToken());
    }

    @GetMapping("/currency")
    public ExchangeDTO getCurrency(){
        return exchangeService.getExchangeRates();
    }
}
