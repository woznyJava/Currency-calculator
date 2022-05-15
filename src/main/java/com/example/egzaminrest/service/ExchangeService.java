package com.example.egzaminrest.service;

import com.example.egzaminrest.client.FeignClient;
import com.example.egzaminrest.configuration.ExchangeProperties;
import com.example.egzaminrest.domain.Result;
import com.example.egzaminrest.exception.FromException;
import com.example.egzaminrest.exception.ToException;
import com.example.egzaminrest.currencyList.ReadFileIntoArrayList;
import com.example.egzaminrest.model.Stats;
import com.example.egzaminrest.repository.ExchangeRepository;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor

public class ExchangeService {
    @Autowired
    private final ExchangeRepository exchangeRepository;
    private final ExchangeProperties exchangeProperties;
//    private Stats stats = new Stats();

    public String getToken() {
        return exchangeProperties.getToken();
    }

    public Double getRate(String from, String to) {
        return getExchangeClientFeign().getRates(exchangeProperties.getToken(), from, to).getRate(to);
    }

    public synchronized void checkFromAndTo(String from, String to) throws IOException {
        List<String> list = new ArrayList<>();
        ReadFileIntoArrayList.getReader(list);
        if (!list.contains(from)) {
            throw new FromException("UNKNOWN_CURRENCY_FROM ");
        }
        if (!list.contains(to)) {
            throw new ToException(" UNKNOWN_CURRENCY_TO");
        }
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
public void save(String from, String to, Double amount){
        Random random = new Random();
    exchangeRepository.save(new Result(random.nextInt(),from,to,amount));
}
public Double convert(String from, String to, Double amount) {
    double value = getRate(from, to);
    return value * amount;
}


//public void updateStats(String from, String to, Double value ) {
//        Double amount = convert(from,to,value);
//        if (stats.getMax() < amount) {
//            stats.updateMax(amount);
//        }
//        stats.updateNumber();
//        stats.updateFrom(from);
//
//    }
}

