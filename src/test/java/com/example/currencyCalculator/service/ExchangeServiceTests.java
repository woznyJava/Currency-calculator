package com.example.currencyCalculator.service;

import com.example.currencyCalculator.domain.Result;
import com.example.currencyCalculator.model.ExchangeRequest;
import com.example.currencyCalculator.model.Stats;
import com.example.currencyCalculator.repository.ExchangeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ExchangeServiceTests {
    @Mock
    private ExchangeRepository exchangeRepository;

    @SpyBean
    FeignExchangeService feignExchangeService;

    @SpyBean
    private ExchangeService exchangeService;

    @Test
    public void shouldReturnFalse() {
        assert !exchangeService.isExchangeValid("TESTOWA");
        assert !exchangeService.isExchangeValid("FAIL");
        assert !exchangeService.isExchangeValid("VALID");
    }

    @Test
    public void shouldReturnTrue() {
        assert exchangeService.isExchangeValid("PLN");
        assert exchangeService.isExchangeValid("USD");
        assert exchangeService.isExchangeValid("EUR");
    }

    @Test
    public void shouldConvert(){
        String from = "USD";
        String to = "PLN";

        var amount1 =  exchangeService.convert(from,to,100.0);
        var amount2 =  exchangeService.convert(from,to,100.0);
        var amount3 =  exchangeService.convert(from,to,25.0);

        assert amount1.equals(amount2);
        assert !amount1.equals(amount3);
    }

    @Test
    public void shouldSave(){
        Result result1 = new Result(1,"USD", "PLN", 100.0, 420.0);
        when( exchangeRepository.findById(anyInt())).thenReturn(Optional.of(result1));

        Optional<Result> result2 = exchangeRepository.findById(1);
        assertEquals(result1.getFrom(), result2.get().getFrom());
        assertEquals(result1.getTo(), result2.get().getTo());
        assertEquals(result1.getAmount(), result2.get().getAmount());

    }
    @Test
    public void shouldReturnStats(){
        Result result1 = new Result(1,"USD", "PLN", 100.0, 420.0);
        Result result2 = new Result(2,"USD", "PLN", 200.0, 840.0);
        List<Result> resultList = new ArrayList<>();

       resultList.add(result1);
       resultList.add(result2);

        exchangeRepository.saveAll(resultList);

        //when(exchangeRepository.findAll()).thenReturn(resultList);

        Stats stats = exchangeService.getStats();

        assertEquals(stats.getMax(), result2.getAmountConverted() );
        assertEquals(stats.getNumberOfInquiries(), 2);
        assertEquals(stats.getTheMostPopularFrom(), "USD" );

    }

    @Test
    public void shouldGetExchange(){
        ExchangeRequest exchangeRequest = new ExchangeRequest("USD", "PLN", 100.0);
    }

}
