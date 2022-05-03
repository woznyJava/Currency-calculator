package com.example.egzaminrest.client;

import com.example.egzaminrest.model.ExchangeDTO;
import feign.Param;
import feign.RequestLine;

public interface FeignClient {
    @RequestLine("GET ?access_key={token}")
    ExchangeDTO getRates(@Param("token") String token);
}
