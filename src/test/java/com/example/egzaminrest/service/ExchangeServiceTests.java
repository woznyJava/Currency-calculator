package com.example.egzaminrest.service;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ExchangeServiceTests {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();
    @Autowired
    MockMvc mvc;

    @Autowired
    private ExchangeService exchangeService;

    @Test
    public void shouldConvertAmount(){
        System.out.println(exchangeService.convert("GBP", "PLN", 100.0));
    }

    @Test
    public void shouldThrowFromException() throws IOException {
        exchangeService.checkFromAndTo("Test", "PLN");
    }

    @Test
    public void shouldThrowToException() throws IOException {
        exchangeService.checkFromAndTo("PLN", "Test");
    }

}
