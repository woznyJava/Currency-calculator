package com.example.egzaminrest.repository;

import com.example.egzaminrest.domain.Result;
import com.example.egzaminrest.service.*;
import com.example.egzaminrest.model.Stats;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ExchangeRepositoryTests {

    @Autowired
    public ExchangeService exchangeService;

    @Autowired
    public ExchangeRepository exchangeRepository;

    @Test
    public void shouldSave(){
        Result result1 = new Result(1,"USD", "PLN", 100.0, 420.0);
        exchangeRepository.save(result1);

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

        Stats stats = exchangeService.getStats();

        assertEquals(stats.getMax(), result2.getAmountConverted() );
        assertEquals(stats.getNumberOfInquiries(), 2);
        assertEquals(stats.getTheMostPopularFrom(), "USD" );
    }
}
