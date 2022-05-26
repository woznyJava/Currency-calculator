package com.example.egzaminrest.service;

import com.example.egzaminrest.bean.FeignBean;
import com.example.egzaminrest.configuration.ExchangeProperties;
import com.example.egzaminrest.model.ExchangeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class FeignExchangeService {

    private final FeignBean feignBean;
    private final ExchangeProperties exchangeProperties;

    public ExchangeDTO getFeignExchange(String from, String to) {
        return feignBean.getFeign().getRates(exchangeProperties.getToken(), from, to);
    }
}
