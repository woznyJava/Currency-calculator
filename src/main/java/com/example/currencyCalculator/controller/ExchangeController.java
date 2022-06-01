package com.example.currencyCalculator.controller;

import com.example.currencyCalculator.model.ExchangeRequest;
import com.example.currencyCalculator.model.Stats;
import com.example.currencyCalculator.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/exchange")
public class ExchangeController {

    private final ExchangeService exchangeService;


    @PostMapping("/calculate")
    public Object currency(@RequestBody ExchangeRequest request) {
        return exchangeService.getExchange(request);
    }

    @GetMapping("/stats")
    public Stats getCalculateStats() {
        return exchangeService.getStats();
    }
}
