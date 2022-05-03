package com.example.egzaminrest.client;

import com.example.egzaminrest.model.ExchangeDTO;
import feign.Param;
import feign.RequestLine;

public interface FeignClient {
    @RequestLine("GET ?apikey={token}&base={from}&symbols={to}")
    ExchangeDTO getRates(@Param("token") String token,
                         @Param("from") String from,
                         @Param("to") String to);
}
