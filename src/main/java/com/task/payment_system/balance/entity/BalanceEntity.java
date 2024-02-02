package com.task.payment_system.balance.entity;

import com.task.payment_system.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity(name = "balance")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class BalanceEntity extends BaseEntity {

    @Column(name = "user_id")
    private String userId;
    private BigDecimal balance;
    private String currency;

}
