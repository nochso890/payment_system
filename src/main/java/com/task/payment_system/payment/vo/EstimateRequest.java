package com.task.payment_system.payment.vo;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EstimateRequest {

    @NotNull(message = "Amount is null")
    private BigDecimal amount;
    @NotNull(message = "Currency is null")
    private String currency;
    @NotNull(message = "Destination is null")
    private String destination;
    @NotNull(message = "UserId is null")
    private String userId;

}
