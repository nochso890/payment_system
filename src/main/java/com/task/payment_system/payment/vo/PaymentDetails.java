package com.task.payment_system.payment.vo;

import com.task.payment_system.utils.CardNumber;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentDetails {
    @NotNull(message = "Card Number is null")
    @CardNumber
    private String cardNumber;
    @NotNull(message = "ExpiryDate is null")
    private String expiryDate;
    @NotNull
    @Min(value = 3, message = "Check the length of the CVV")
    private String cvv;
}
