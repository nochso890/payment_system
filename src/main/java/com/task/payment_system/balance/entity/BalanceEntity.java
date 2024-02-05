package com.task.payment_system.balance.entity;

import com.task.payment_system.common.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Entity(name = "balance")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class BalanceEntity extends BaseEntity {

    @Column(name = "user_id")
    private String userId;
    private BigDecimal balance;
    private String currency;

}
