package com.example.currencyCalculator.client;

import com.example.currencyCalculator.model.ExchangeDTO;
import feign.Param;
import feign.RequestLine;
import org.springframework.stereotype.Component;

@Component
public interface FeignClient {
    @RequestLine("GET ?apikey={token}&base={from}&symbols={to}")
    ExchangeDTO getRates(@Param("token") String token,
                         @Param("from") String from,
                         @Param("to") String to);
}
