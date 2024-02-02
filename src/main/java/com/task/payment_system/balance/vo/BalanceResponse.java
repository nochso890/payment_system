package com.task.payment_system.balance.vo;

import com.task.payment_system.balance.entity.BalanceEntity;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BalanceResponse {

    private String userId;
    private BigDecimal balance;
    private String currency;

    public BalanceResponse(BalanceEntity entity){
        this.userId = entity.getUserId();
        this.balance = entity.getBalance();
        this.currency = entity.getCurrency();
    }

}
