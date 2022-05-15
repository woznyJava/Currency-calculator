package com.example.egzaminrest.controller;

import com.example.egzaminrest.domain.Result;
import com.example.egzaminrest.model.Stats;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest
@AutoConfigureMockMvc
public class ExchangeControllerTests {
    @Autowired
    private ExchangeController exchangeController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
        assertThat(exchangeController).isNotNull();
    }

    @Test
    public void shouldReturnValidCurrencyInDouble() throws Exception {
        mockMvc.perform(post("/exchange/calculate")
                        .param("from", "USD")
                        .param("to", "PLN")
                        .param("amount", String.valueOf(25.0))
                ).andExpect(status().isOk())
                 .andDo(handler -> {
                    assert Double.parseDouble(handler.getResponse().getContentAsString()) > 0.0;
                 });
    }
    
    @Test
    public void shouldReturnStats() throws Exception {
        mockMvc.perform(post("/exchange/calculate")
                .param("from", "USD")
                .param("to", "PLN")
                .param("amount", String.valueOf(25.0))
        );

        mockMvc.perform(get("/exchange/stats"))
                .andExpect(status().isOk())
                .andDo(handler -> {
                    assert handler.getResponse().getContentLength() > 0;
                });

    }
}
