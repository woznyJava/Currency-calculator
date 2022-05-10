package com.example.egzaminrest.controller;

import com.example.egzaminrest.model.Stats;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExchangeControllerTests {

    @Autowired
private ExchangeController exchangeController;

    @Test
    public void contextLoads(){
        assertThat(exchangeController).isNotNull();
    }


        @Autowired
        private MockMvc mockMvc;

        @Test
        public void shouldReturnStats() throws Exception {
            exchangeController.getCurrency("GBP","PLN",200.00);
            Stats stats = new Stats();
            stats.updateForm("GBP");
            stats.updateMax(200.00);
            stats.updateNumber();
            this.mockMvc.perform(get("/stats")).andDo(print()).andExpect(status().isOk()).equals(stats);

        }
    }

