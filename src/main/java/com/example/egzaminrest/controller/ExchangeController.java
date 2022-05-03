package com.example.egzaminrest.controller;

import com.example.egzaminrest.model.ExchangeDTO;
import com.example.egzaminrest.model.Result;
import com.example.egzaminrest.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/exchange")
    public Result getCurrency(@RequestParam("from") String from,
                              @RequestParam("to") String to,
                              @RequestParam("amount") Double amount) {
        var rate = exchangeService.getRate(from, to);

        Double value = amount * rate;

        return new Result(from, to, value);
    }
}
