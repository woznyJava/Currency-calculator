package com.example.egzaminrest.service;

import com.example.egzaminrest.client.FeignClient;
import com.example.egzaminrest.configuration.ExchangeProperties;
import com.example.egzaminrest.domain.Result;
import com.example.egzaminrest.repository.ResultRepository;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeProperties exchangeProperties;
    private ResultRepository resultRepository;

    public String getToken() {
        return exchangeProperties.getToken();
    }

    public Double getRate(String from, String to) {
        return getExchangeClientFeign().getRates(exchangeProperties.getToken(), from, to).getRate(to);
    }
    public void save(Result result){
        resultRepository.save(result);
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

    public void getTheMostPopularFrom(){
         Stream.of(resultRepository.findAll())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(System.out::println);
    }


//    public void updateStatics(String from, String to, Double amount) {
//    }
        }
