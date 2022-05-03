package com.example.egzaminrest.service;

import com.example.egzaminrest.client.FeignClient;
import com.example.egzaminrest.configuration.ExchangeProperties;
import com.example.egzaminrest.model.ExchangeDTO;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeProperties exchangeProperties;

    public String getToken() {
        return exchangeProperties.getToken();
    }

    public Double getRate(String from, String to) {
        return getExchangeClientFeign().getRates(exchangeProperties.getToken(), from, to).getRate(to);
    }

    private static FeignClient getExchangeClientFeign() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(FeignClient.class))
                .logLevel(Logger.Level.FULL)
                .target(FeignClient.class, "https://api.apilayer.com/exchangerates_data/latest");
    }
}
