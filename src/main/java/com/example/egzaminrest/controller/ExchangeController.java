package com.example.egzaminrest.controller;

import com.example.egzaminrest.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/exchange")
public class ExchangeController {

    private final ExchangeService exchangeService;
    @GetMapping("/token")
    public Map<String, String> getToken() {
        return Map.of("token", exchangeService.getToken());
    }

    @PostMapping("/calculate")
    public Double Currency(@RequestParam("from") String from,
                              @RequestParam("to") String to,
                              @RequestParam("amount") Double amount) throws IOException {


        exchangeService.checkFromAndTo(from, to);
        exchangeService.save(from,to,amount);
        return exchangeService.convert(from,to,amount);
    }

    @GetMapping("/stats")
    public String getCalculateStats() {
        return exchangeService.getStats();
    }
}
