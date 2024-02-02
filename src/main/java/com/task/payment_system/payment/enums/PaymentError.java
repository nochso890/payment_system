package com.task.payment_system.payment.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentError {

    NOT_FOUND_PAYMENT_DETAILS("600","결제 상세 정보를 찾을 수 없습니다."),
    CREDIT_CARD_EXPIRED("601","카드 유효 일자가 지났습니다."),
    INSUFFICIENT_BALANCE("700", "잔액이 부족합니다."),

    ;

    private final String errorCode;
    private final String errorMessage;
}
