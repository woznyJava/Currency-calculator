package com.example.egzaminrest.service;

import com.example.egzaminrest.domain.Result;
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

    public ExchangeResponse getExchange(ExchangeRequest request) {

        try {
            feignExchangeService.getFeignExchange(request.getFrom(), request.getTo()).getRates();
        } catch (Exception e) {
            return new ExchangeResponse("UNKNOWN_CURRENCY_FORMAT", 0.0, false, e.getMessage());
        }
        Double convertedAmount = convert(request);

        Result result = new Result(null, request.getFrom(), request.getTo(), request.getAmount(), convertedAmount);
        save(result);

        return new ExchangeResponse("", convertedAmount, true, "");
    }

    private Double convert(ExchangeRequest request) {
        double value = getRate(request.getFrom(), request.getTo());
        return value * request.getAmount();
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

