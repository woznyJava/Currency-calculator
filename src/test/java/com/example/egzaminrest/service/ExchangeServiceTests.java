package com.example.egzaminrest.service;

import com.example.egzaminrest.domain.Result;
import com.example.egzaminrest.model.ExchangeRequest;
import com.example.egzaminrest.model.Stats;
import com.example.egzaminrest.repository.ExchangeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

//Nwm czy mozna mockowac feign ale wrzucilem w razie czego
    @Mock
    FeignExchangeService feignExchangeService;

    @InjectMocks
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
//Null wyrzuca
    @Test
    public void shouldConvert(){
        String from = "USD";
        String to = "PLN";
        Double amount = 100.0;
        Double convertAmount = 420.0;
        double rate = 4.2;
        rate = feignExchangeService.getFeignExchange(from,to).getRate(to);
        assert exchangeService.convert(from,to,amount).equals(convertAmount);
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
        when(exchangeRepository.findAll()).thenReturn(resultList);
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
