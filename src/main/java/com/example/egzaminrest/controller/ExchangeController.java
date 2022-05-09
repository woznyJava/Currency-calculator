package com.example.egzaminrest.controller;

import com.example.egzaminrest.domain.Result;
import com.example.egzaminrest.model.Stats;
import com.example.egzaminrest.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exchange")
public class ExchangeController {

    private final ExchangeService exchangeService;
    private Stats stats = new Stats(1.0,0,"EUR");

    @GetMapping("/token")
    public Map<String, String> getToken() {
        return Map.of("token", exchangeService.getToken());
    }

    @PostMapping("/calculate")
    public void getCurrency(@RequestParam("from") String from,
                              @RequestParam("to") String to,
                              @RequestParam("amount") Double amount) {
//Result result = new Result(from,to,amount);

//            exchangeService.updateStatics(from,to, amount);
            exchangeService.save(new Result(new Random().nextLong(), from,to, amount));

//        int numer = stats.getNumberOfInquiries() +1;
//        stats.setNumberOfInquiries(numer);
//        if (amount > stats.getMax()){
//            stats.setMax(amount);
//        }
    }

    // ogarnac to wszystko w metodzie w servisie ^ 4
    // przy okazji adnotacja o transakcjach
    @GetMapping("/stats")
    public Stats getCalculateStats() {
        exchangeService.getTheMostPopularFrom();
        return stats;
    }
    @GetMapping("/from")
    public void getFrom() {
         exchangeService.getTheMostPopularFrom();
    }

}
