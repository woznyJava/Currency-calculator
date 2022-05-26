package com.example.egzaminrest.bean;

import com.example.egzaminrest.client.FeignClient;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignBean {

    @Bean
    public FeignClient getFeign() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(FeignClient.class))
                .logLevel(Logger.Level.FULL)
                .target(FeignClient.class, "https://api.apilayer.com/exchangerates_data/latest");
    }
}
