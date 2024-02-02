package com.task.payment_system.payment.entity;

import com.task.payment_system.common.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@Entity(name = "credit_card_payment")
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardPaymentEntity extends BaseEntity {

    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "expiry_date")
    private String expiryDate;
    private Integer cvv;
    private boolean status;

}
