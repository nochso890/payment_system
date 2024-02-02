package com.task.payment_system.balance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;
import com.task.payment_system.balance.entity.BalanceEntity;
import com.task.payment_system.balance.service.BalanceService;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
class BalanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BalanceService balanceService;


    @Test
    void testGetBalance() throws Exception {
        String userId = "12345";
        BigDecimal expectedBalance = BigDecimal.valueOf(1000.00).setScale(2);
        var expectedEntity = BalanceEntity.builder()
            .balance(expectedBalance)
            .build();

        when(balanceService.getBalance(anyString())).thenReturn(expectedEntity);

        var resultActions = mockMvc.perform(get("/api/payment/balance/" + userId)
            .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andDo(print());

        String jsonResponse = resultActions.andReturn().getResponse().getContentAsString();
        assertThat(BigDecimal.valueOf((Double) JsonPath.read(jsonResponse, "$.data.balance"))).isEqualByComparingTo(
            expectedBalance);


    }
}

