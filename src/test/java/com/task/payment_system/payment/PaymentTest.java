package com.task.payment_system.payment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.task.payment_system.commission.service.CommissionService;
import com.task.payment_system.payment.service.PaymentService;
import com.task.payment_system.payment.vo.ApprovalRequest;
import com.task.payment_system.payment.vo.EstimateRequest;
import com.task.payment_system.payment.vo.PaymentDetails;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PaymentController paymentController;

    @Autowired
    PaymentService paymentService;

    @Autowired
    CommissionService commissionService;

    @Test
    @DisplayName("결제 예상 결과 조회")
    void testEstimate() throws Exception {
        EstimateRequest estimateRequest = new EstimateRequest();
        estimateRequest.setUserId("12345");
        estimateRequest.setAmount(BigDecimal.valueOf(150.00).setScale(2));
        estimateRequest.setDestination("merchantId123");
        estimateRequest.setCurrency("USD");

        var resultActions = mockMvc.perform(MockMvcRequestBuilders
            .post("/api/payment/estimate")
            .content(new ObjectMapper().writeValueAsString(estimateRequest))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print());

        String jsonResponse = resultActions.andReturn().getResponse().getContentAsString();
        assertThat(
            BigDecimal.valueOf((Double) JsonPath.read(jsonResponse, "$.data.estimatedTotal"))).isEqualByComparingTo(
            BigDecimal.valueOf(155.00));
        assertThat(BigDecimal.valueOf((Double) JsonPath.read(jsonResponse, "$.data.fees"))).isEqualByComparingTo(
            BigDecimal.valueOf(5.00));
    }

    @Test
    @DisplayName("결제 승인 요청")
    void testApproval() throws Exception {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setCardNumber("1234-5678-9123-4567");
        paymentDetails.setCvv("123");
        paymentDetails.setExpiryDate("12/24");

        ApprovalRequest approvalRequest = new ApprovalRequest();
        approvalRequest.setUserId("12345");
        approvalRequest.setAmount(BigDecimal.valueOf(200.00));
        approvalRequest.setCurrency("USD");
        approvalRequest.setMerchantId("merchantId123");
        approvalRequest.setPaymentMethod("creditCard");
        approvalRequest.setPaymentDetails(paymentDetails);

        var resultActions = mockMvc.perform(MockMvcRequestBuilders
            .post("/api/payment/approval")
            .content(new ObjectMapper().writeValueAsString(approvalRequest))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print());

        String jsonResponse = resultActions.andReturn().getResponse().getContentAsString();
        assertThat(JsonPath.read(jsonResponse, "$.data.paymentId").toString()).isEqualTo("paymentId12345");

    }

}
