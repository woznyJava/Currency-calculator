package com.example.currencyCalculator.service;

import com.example.currencyCalculator.bean.FeignBean;
import com.example.currencyCalculator.configuration.ExchangeProperties;
import com.example.currencyCalculator.model.ExchangeDTO;
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
