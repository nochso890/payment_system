package com.task.payment_system.payment;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.payment_system.payment.service.PaymentService;
import com.task.payment_system.payment.vo.EstimateRequest;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Test
    void testCallEstimate() throws Exception {
        EstimateRequest estimateRequest = new EstimateRequest();
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/payment/estimate")
                .content(new ObjectMapper().writeValueAsString(estimateRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void testGetEstimate() throws Exception {
        EstimateRequest estimateRequest = new EstimateRequest();
        estimateRequest.setAmount(new BigDecimal(150.00));
        estimateRequest.setCurrency("USD");
        estimateRequest.setDestination("merchantId123");
        estimateRequest.setUserId("12345");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/payment/estimate")
                .content(new ObjectMapper().writeValueAsString(estimateRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }
}
