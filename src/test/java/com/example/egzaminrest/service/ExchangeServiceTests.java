package com.example.egzaminrest.service;

import com.example.egzaminrest.configuration.ExchangeProperties;

import com.example.egzaminrest.model.ExchangeDTO;
import com.example.egzaminrest.model.ExchangeRequest;
import com.example.egzaminrest.model.ExchangeResponse;
import com.example.egzaminrest.repository.ExchangeRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ExchangeServiceTests {

    @Autowired
    private ExchangeService exchangeService;

//
//   @InjectMocks
//   private ExchangeService exchangeService;

//    @Test
//    public void shouldConvertAmountCorrectly() {
//
//        double value = 4.2;
//        double amount = 100.0;
//        double expectedValue = value * amount;
//        ExchangeRequest exchangeRequest = new ExchangeRequest("USD", "PLN", amount);
//        when(exchangeService.getExchange(exchangeRequest))
//                .thenReturn(new ExchangeResponse("", expectedValue, true, ""));
//        ExchangeResponse exchangeResponse = exchangeService.getExchange(exchangeRequest);
//    }

    @Test
    public void shouldReturnFalse() {
        assert !exchangeService.isExchangeValid("BADEXCHANGE");
        assert !exchangeService.isExchangeValid("WOW");
        assert !exchangeService.isExchangeValid("NICE");
    }

    @Test
    public void shouldReturnTrue() {
        assert exchangeService.isExchangeValid("PLN");
        assert exchangeService.isExchangeValid("USD");
        assert exchangeService.isExchangeValid("EUR");
    }


//    @Test
//    public void shouldConvertAmountCorrectly() {
//
//        double value = 4.2;
//        double amount = 100.0;
//        double expectedValue = value * amount;
//
//        ExchangeDTO mockedRates = new ExchangeDTO();
//        HashMap<String, Double> mockedHashMap = new HashMap<String, Double>(1);
//        mockedHashMap.put("PLN", value);
//        mockedRates.setRates(mockedHashMap);
//
//        ExchangeRequest request = new ExchangeRequest("USD", "PLN", amount);
//        when(feignExchangeService.getFeignExchange("USD", "PLN")).thenReturn(mockedRates);
//
//        ExchangeResponse result = exchangeService.getExchange(request);
//
//        result.getAmount().compareTo(expectedValue);
//        result.getSuccess().compareTo(true);
//    }


//    @Test
//    public void shouldFailWhenCurrencyNotExists() {
//
//        double amount = 100.0;
//
//        ExchangeDTO mockedRates = new ExchangeDTO();
//        HashMap<String, Double> mockedHashMap = new HashMap<String, Double>(1);
//        mockedRates.setRates(mockedHashMap);
//
//        ExchangeRequest request = new ExchangeRequest("AAA", "PLN", amount);
//        given(feignExchangeService.getFeignExchange("AAA", "PLN")).willAnswer(invocation -> {
//            throw new Exception("invalid currency");
//        });
//
//        ExchangeResponse result = exchangeService.getExchange(request);
//
//        result.getAmount().compareTo(0.0);
//        result.getSuccess().compareTo(false);
//    }
}
