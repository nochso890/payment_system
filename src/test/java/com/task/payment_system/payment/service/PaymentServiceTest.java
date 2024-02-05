package com.task.payment_system.payment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.task.payment_system.commission.repository.CommissionRateRepository;
import com.task.payment_system.commission.service.CommissionService;
import com.task.payment_system.payment.entity.CreditCardPaymentEntity;
import com.task.payment_system.payment.repository.CreditCardPaymentRepository;
import com.task.payment_system.payment.repository.PaymentRepository;
import com.task.payment_system.payment.vo.PaymentDetails;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private CreditCardPaymentRepository creditCardPaymentRepository;
    @Mock
    private CommissionRateRepository commissionRateRepository;
    @Mock
    private PaymentRepository paymentRepository;
    @InjectMocks
    private PaymentService paymentService;
    @InjectMocks
    private CommissionService commissionService;

    @Test
    @DisplayName("카드번호 조회")
    void testSearchPaymentByCardNumber() {
        String cardNumber = "1234-5678-9123-4567";
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setCardNumber(cardNumber);
        CreditCardPaymentEntity expectEntity = CreditCardPaymentEntity.builder().cardNumber(cardNumber).build();

        when(creditCardPaymentRepository.findByCardNumber(cardNumber)).thenReturn(Optional.of(expectEntity));

        CreditCardPaymentEntity result = paymentService.searchPaymentByCardNumber(paymentDetails);

        assertEquals(expectEntity.getCardNumber(), result.getCardNumber());

        verify(creditCardPaymentRepository, times(1)).findByCardNumber(cardNumber);

    }

//    @Test
//    void testApproval() {
//
//        PaymentDetails paymentDetails = new PaymentDetails();
//        paymentDetails.setCardNumber("1234-5678-9123-4567");
//        paymentDetails.setExpiryDate("12/24");
//        paymentDetails.setCvv("123");
//
//        ApprovalRequest approvalRequest = new ApprovalRequest();
//        approvalRequest.setUserId("12345");
//        approvalRequest.setAmount(BigDecimal.valueOf(200.00));
//        approvalRequest.setCurrency("USD");
//        approvalRequest.setPaymentMethod("creditCard");
//        approvalRequest.setPaymentDetails(paymentDetails);
//
//        CommissionRateEntity commissionRateEntity =
//            CommissionRateEntity.builder()
//                .id(1L)
//                .merchantId("merchantId123")
//                .amount(BigDecimal.valueOf(5.00))
//                .build();
//
//        PaymentEntity payment = PaymentEntity.builder().build();
//
//        String cardNumber = approvalRequest.getPaymentDetails().getCardNumber();
//        CreditCardPaymentEntity creditCardPaymentEntity = CreditCardPaymentEntity.builder().cardNumber(cardNumber).build();
//
//        when(creditCardPaymentRepository.findByCardNumber(any())).thenReturn(Optional.of(creditCardPaymentEntity));
//        when(commissionService.getCommission(any())).thenReturn(Optional.of(commissionRateEntity));
//        when(paymentRepository.save(any())).thenReturn(Optional.of(payment));
//
//        PaymentEntity result = paymentService.approval(approvalRequest);
//
//        assertNotNull(result);
//        assertEquals(ApprovalStatus.APPROVED, result.getApprovalStatus());
//
//    }
}
