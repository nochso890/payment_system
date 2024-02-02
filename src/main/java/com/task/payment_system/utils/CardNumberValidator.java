package com.task.payment_system.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CardNumberValidator implements ConstraintValidator<CardNumber,String> {

    @Override
    public boolean isValid(String cardNumber, ConstraintValidatorContext constraintValidatorContext) {
        if(cardNumber == null || cardNumber.isBlank()){
            return false;
        }
        var replace = cardNumber.replace("-", "");
        return replace.length() == 16;
    }
}
