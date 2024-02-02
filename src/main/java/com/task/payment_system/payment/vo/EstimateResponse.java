package com.task.payment_system.payment.vo;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EstimateResponse {

    private BigDecimal estimatedTotal;
    private BigDecimal fees;
    private String currency;

    public EstimateResponse(EstimateRequest request, BigDecimal fees){
        this.estimatedTotal = request.getAmount().add(fees);
        this.fees = fees;
        this.currency = request.getCurrency();
    }

}
