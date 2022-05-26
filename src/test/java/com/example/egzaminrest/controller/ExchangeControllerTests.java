package com.example.egzaminrest.controller;

import com.example.egzaminrest.model.ExchangeRequest;
import com.example.egzaminrest.service.ExchangeService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ExchangeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    ExchangeService exchangeService;


    @InjectMocks
    ExchangeController exchangeController;

    @Test
    public void contextLoads() {
        assertThat(exchangeController).isNotNull();
    }

    @Test
    public void shouldConvert() throws Exception {
        ExchangeRequest exchangeRequest = new ExchangeRequest("USD", "PLN", 100.0);

        Gson gson = new Gson();
        String json = gson.toJson(exchangeRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/exchange/calculate")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));
    }


    @Test
    public void shouldReturnStats() throws Exception {

        ExchangeRequest exchangeRequest = new ExchangeRequest("USD", "PLN", 100.0);
        Gson gson = new Gson();
        String json = gson.toJson(exchangeRequest);

        mockMvc.perform(
                get("/exchange/stats")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    //@Test
    //public void shouldReturnStats() throws Exception {
//    Gson gson = new Gson();
//    String json = gson.toJson(exchangeRequest);
    //
//    ExchangeRequest exchangeRequest = new ExchangeRequest("USD", "PLN", 100.0);
    //
    //    mockMvc.perform(MockMvcRequestBuilders.post("/exchange/calculate")
    //                    .contentType(MediaType.APPLICATION_JSON)
    //                    .content(json));
    //
    //    mockMvc.perform(get("/exchange/stats")
    //            .contentType(MediaType.APPLICATION_JSON))
    //            .andExpect(status().isOk())
    //            .andExpect(content()
    //            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    //            .andExpect(jsonPath("$.max").value("428.908"))
    //            .andExpect(jsonPath("$.numberOfInquiries").value("2"))
    //            .andExpect(jsonPath("$.theMostPopularForm").value("USD"))
    //            .andExpect(jsonPath("$.forms").value("{}"));
}

