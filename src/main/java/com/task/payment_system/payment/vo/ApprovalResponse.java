package com.task.payment_system.payment.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.task.payment_system.payment.entity.PaymentEntity;
import com.task.payment_system.payment.enums.ApprovalStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApprovalResponse {

    private String paymentId;
    private ApprovalStatus status;
    private BigDecimal amount;
    private String currency;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime timestamp;


    public ApprovalResponse(PaymentEntity entity) {
        this.paymentId = entity.getPaymentId();
        this.status = entity.getApprovalStatus();
        this.amount = entity.getAmount().setScale(2);
        this.currency = entity.getCurrency();
        this.timestamp = entity.getCreatedAt();
    }
}
