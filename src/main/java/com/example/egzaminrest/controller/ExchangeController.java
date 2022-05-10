package com.example.egzaminrest.controller;

import com.example.egzaminrest.domain.Result;
import com.example.egzaminrest.model.Stats;
import com.example.egzaminrest.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exchange")
public class ExchangeController {
    private final ExchangeService exchangeService;
    private Stats stats = new Stats();

    @GetMapping("/token")
    public Map<String, String> getToken() {
        return Map.of("token", exchangeService.getToken());
    }

    @PostMapping("/calculate")
    public Result getCurrency(@RequestParam("from") String from,
                              @RequestParam("to") String to,
                              @RequestParam("amount") Double amount) {
        Double value = 0.00;
        exchangeService.checkFromAndTo(from, to);


//        try {
//             value = exchangeService.getRate(from, to);
//        } catch (Exception err) {
//            throw new RuntimeException("Unknown worth");
//        }
//        value = exchangeService.getRate(from, to);
        stats.updateMax(amount * value);
        stats.updateNumber();
        stats.updateForm(from);

        return new Result(from, to, value);
    }

    @GetMapping("/stats")
    public Stats getCalculateStats() {
        return stats;
    }
}
