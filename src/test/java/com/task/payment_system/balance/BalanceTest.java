package com.task.payment_system.balance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class BalanceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("잔액조회")
    void testGetBalance() throws Exception {
        String userId = "12345";
        BigDecimal expectBalance = BigDecimal.valueOf(1000.00).setScale(2);
        String expectCurrency = "USD";

        var resultActions = mockMvc.perform(get("/api/payment/balance/" + userId)
            .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andDo(print());

        String jsonResponse = resultActions.andReturn().getResponse().getContentAsString();
        assertThat(jsonResponse).isNotNull();
        assertThat(BigDecimal.valueOf((Double) JsonPath.read(jsonResponse, "$.data.balance"))).isEqualByComparingTo(
            expectBalance);
        assertThat(JsonPath.read(jsonResponse, "$.data.currency").toString()).isEqualTo(expectCurrency);
    }

}
