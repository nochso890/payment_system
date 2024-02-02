package com.task.payment_system.payment.vo;

import com.task.payment_system.payment.entity.PaymentEntity;
import com.task.payment_system.payment.enums.ApprovalStatus;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApprovalRequest {

    private String userId;
    private BigDecimal amount;
    private String currency;
    private String merchantId;
    private String paymentMethod;
    @Valid
    private PaymentDetails paymentDetails;

    public PaymentEntity toEntity(long creditCardId,long commissionId){
        return PaymentEntity.builder()
            .paymentId("paymentId"+userId)
            .userId(userId)
            .paymentMethod(paymentMethod)
            .amount(amount)
            .currency(currency)
            .merchantId(merchantId)
            .creditCardId(creditCardId)
            .commissionId(commissionId)
            .build();
    }

}
