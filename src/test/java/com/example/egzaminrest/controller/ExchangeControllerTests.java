package com.example.egzaminrest.controller;

import com.example.egzaminrest.model.ExchangeRequest;
import com.example.egzaminrest.service.ExchangeService;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ExchangeControllerTests {

    @Mock
    ExchangeService exchangeService;
    @InjectMocks
    ExchangeController exchangeController;
    @Autowired
    private MockMvc mockMvc;

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
    public void shouldReturnFromUnknownError() throws Exception {
        ExchangeRequest exchangeRequest = new ExchangeRequest("HAHA", "PLN", 100.0);

        Gson gson = new Gson();
        String json = gson.toJson(exchangeRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/exchange/calculate")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andDo((var handler) -> {
                    var content = handler.getResponse().getContentAsString();

                    assertThat(content == "UNKNOWN_FROM_CURRENCY_FORMAT");
                })
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnToUnknownError() throws Exception {
        ExchangeRequest exchangeRequest = new ExchangeRequest("PLN", "HAHA", 100.0);

        Gson gson = new Gson();
        String json = gson.toJson(exchangeRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/exchange/calculate")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andDo((var handler) -> {
                    var content = handler.getResponse().getContentAsString();

                    assertThat(content == "UNKNOWN_TO_CURRENCY_FORMAT");
                })
                .andExpect(status().isBadRequest());
    }


    @Test
    public void shouldReturnStats() throws Exception {
        ExchangeRequest exchangeRequest = new ExchangeRequest("USD", "PLN", 100.0);
        Gson gson = new Gson();
        String json = gson.toJson(exchangeRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/exchange/calculate")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));

        mockMvc.perform(
                        get("/exchange/stats")
                                .contentType(APPLICATION_JSON)
                                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andDo((var handler) -> {
                    var content = handler.getResponse().getContentAsString();
                    JSONObject jsonData = new JSONObject(content);

                    assertThat((Integer) jsonData.get("numberOfInquiries") == 1);
                    assertThat((String) jsonData.get("theMostPopularFrom") == "USD");
                });
    }

}

