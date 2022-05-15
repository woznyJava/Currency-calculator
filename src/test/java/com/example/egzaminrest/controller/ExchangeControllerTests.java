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
    public void shouldReturnStats() throws Exception {
//        exchangeController.c("GBP", "PLN", 200.00);
//        Stats stats = new Stats();
//        stats.updateFrom("GBP");
//        stats.updateMax(200.00);
//        stats.updateNumber();

        mockMvc.perform(post("/stats"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.from", is("GBP")))
                        .andExpect((ResultMatcher) jsonPath("$.to", is("PLN")))
                        .andExpect((ResultMatcher) jsonPath("$.amount", is(200.00)));


    }
@Test
public void calculateTest() throws Exception {
    mockMvc.perform(post("/stats"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect((ResultMatcher) jsonPath("$.from", is("UncorrectFrom")))
            .andExpect((ResultMatcher) jsonPath("$.to", is("PLN")))
            .andExpect((ResultMatcher) jsonPath("$.amount", is(200.00)));

}
}
