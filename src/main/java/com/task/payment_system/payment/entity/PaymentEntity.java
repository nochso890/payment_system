package com.task.payment_system.payment.entity;

import com.task.payment_system.common.base.BaseEntity;
import com.task.payment_system.payment.enums.ApprovalStatus;
import com.task.payment_system.payment.enums.ApprovalStatus.ApprovalStatusConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@SuperBuilder
@ToString(callSuper = true)
@DynamicInsert
@DynamicUpdate
@Entity(name = "payment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PaymentEntity extends BaseEntity {

    @Column(name = "payment_id")
    private String paymentId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "payment_method")
    private String paymentMethod;
    private BigDecimal amount;
    private String currency;
    @Column(name = "merchant_id")
    private String merchantId;
    @Column(name = "credit_card_id")
    private Long creditCardId;
    @Column(name = "commission_id")
    private Long commissionId;
    @Column(name = "approval_status")
    @Convert(converter = ApprovalStatusConverter.class)
    @Builder.Default
    private ApprovalStatus approvalStatus = ApprovalStatus.WAITING;

}
