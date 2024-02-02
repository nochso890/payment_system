package com.task.payment_system.balance;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;
import com.task.payment_system.balance.service.BalanceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationBalanceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BalanceController balanceController;
    @Autowired
    private BalanceService balanceService;


    @Test
    @DisplayName("잔액조회")
    void testGetBalance() throws Exception {
        String userId = "12345";

        var resultActions = mockMvc.perform(get("/api/payment/balance/" + userId)
            .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andDo(print());

        String jsonResponse = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(JsonPath.read(jsonResponse, "$.data").toString());
    }

}
