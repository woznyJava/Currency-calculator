package com.example.egzaminrest.controller;

import com.example.egzaminrest.domain.Result;
import com.example.egzaminrest.model.ExchangeDTO;
import com.example.egzaminrest.model.Stats;
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
//    private final Stats stats = new Stats();
//NIE MOZE BYC BO SIE ZMEINIA
    @GetMapping("/token")
    public Map<String, String> getToken() {
        return Map.of("token", exchangeService.getToken());
    }

    @PostMapping("/calculate")
    public Double Currency(@RequestParam("from") String from,
                              @RequestParam("to") String to,
                              @RequestParam("amount") Double amount) throws IOException {

//       getToken();
//        Double value = 0.00;
        exchangeService.checkFromAndTo(from, to);
        exchangeService.save(from,to,amount);
//        exchangeService.updateStats(from,to, amount);

//        value = exchangeService.getRate(from, to);
//        stats.updateMax(amount * value);
//        stats.updateNumber();
//        stats.updateForm(from);
        //^ to wszystko w 1 metode w serwisie
        //łacznie z new result

//
        return exchangeService.convert(from,to,amount);
    }

    @GetMapping("/stats")
    public ExchangeService getCalculateStats() {
        return exchangeService.ge;
    }
}
