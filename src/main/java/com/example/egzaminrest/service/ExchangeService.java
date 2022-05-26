package com.example.egzaminrest.service;

import com.example.egzaminrest.domain.Result;
import com.example.egzaminrest.model.ExchangeError;
import com.example.egzaminrest.model.ExchangeRequest;
import com.example.egzaminrest.model.ExchangeResponse;
import com.example.egzaminrest.model.Stats;
import com.example.egzaminrest.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor

public class ExchangeService {
    private final FeignExchangeService feignExchangeService;
    private final ExchangeRepository exchangeRepository;

    public Object getExchange(ExchangeRequest request) {
        String from = request.getFrom();
        String to = request.getTo();
        Double amount = request.getAmount();

        if (!isExchangeValid(from)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("UNKNOWN_FROM_CURRENCY_FORMAT");
        }

        if (!isExchangeValid(to)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("UNKNOWN_TO_CURRENCY_FORMAT");
        }

        Double convertedAmount = convert(from, to, amount);

        Result result = new Result(null, from, to, amount, convertedAmount);
        save(result);

        return new ExchangeResponse(convertedAmount);
    }

    public Double convert(String from, String to, Double amount) {
        Double rate = feignExchangeService.getFeignExchange(from, to).getRate(to);

        return amount * rate;
    }

    public Boolean isExchangeValid(String exchange) {
        try {
            convert(exchange, exchange, 100.0);
        } catch (Exception err) {
            return false;
        }

        return true;
    }

    public Double getRate(String from, String to) {
        return feignExchangeService.getFeignExchange(from, to).getRate(to);
    }

    public void save(Result result) {
        exchangeRepository.save(result);
    }

    public Stats getStats() {
        List<Result> listResults = exchangeRepository.findAll();
        Iterator<Result> iterator = listResults.iterator();
        Stats stats = new Stats();

        while (iterator.hasNext()) {
            Result result = iterator.next();
            Double amountConverted = result.getAmountConverted();

            if (stats.getMax() < amountConverted) {
                stats.updateMax(amountConverted);
            }
            stats.increaseNumber();
            stats.updateFroms(result.getFrom());
        }

        return stats;
    }
}

