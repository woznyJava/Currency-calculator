package com.example.egzaminrest.service;

import com.example.egzaminrest.bean.FeignBean;
import com.example.egzaminrest.bean.ReaderConfig;
import com.example.egzaminrest.client.FeignClient;
import com.example.egzaminrest.configuration.ExchangeProperties;
import com.example.egzaminrest.domain.Result;
import com.example.egzaminrest.exception.FromException;
import com.example.egzaminrest.exception.ToException;
import com.example.egzaminrest.model.Stats;
import com.example.egzaminrest.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
@RequiredArgsConstructor

public class ExchangeService {
    private final FeignBean feignBean;
    private final ReaderConfig readerConfig;
    private final ExchangeRepository exchangeRepository;
    private final ExchangeProperties exchangeProperties;

    public String getToken() {
        return exchangeProperties.getToken();
    }

    public Double getRate(String from, String to) {
        return getExchangeClientFeign().getRates(exchangeProperties.getToken(), from, to).getRate(to);
    }

    public void checkFromAndTo(String from, String to) throws IOException {
        List<String> list = new ArrayList<>();
        readerConfig.getReader().getReader(list);

        if (!list.contains(from)) {
            throw new FromException("UNKNOWN_CURRENCY_FROM");
        }
        if (!list.contains(to)) {
            throw new ToException(" UNKNOWN_CURRENCY_TO");
        }
    }

    private  FeignClient getExchangeClientFeign() {
        return feignBean.getFeign();
    }
    public void save(String from, String to, Double amount) {
        exchangeRepository.save(new Result(null, from,to,amount));

    }


    public Double convert(String from, String to, Double amount) {
        double value = getRate(from, to);
        return value * amount;
    }

    public String getStats() {
        List<Result> listResults = exchangeRepository.findAll();
        Iterator<Result> iterator = listResults.iterator();
        Stats statsReturn = new Stats();

        while (iterator.hasNext()) {
            Result result = iterator.next();
            Double amount = convert(result.getFrom(), result.getTo(), result.getAmount());
            Stats stats = new Stats(0.0, 0, " ");
            if (stats.getMax() < amount) {
                stats.updateMax(amount);
            }
            stats.updateNumber();
            stats.updateFrom(result.getFrom());
            statsReturn.setMax(stats.getMax());
            statsReturn.setTheMostPopularForm(stats.getTheMostPopularForm());
            statsReturn.setNumberOfInquiries(stats.getNumberOfInquiries() + stats.getNumberOfInquiries());
        }
        return statsReturn.toString();
    }
}

