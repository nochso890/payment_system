package com.task.payment_system.balance.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BalanceError {

    NOT_FOUND_USER_BALANCE("800","해당 사용자 잔액정보를 찾을수 없습니다."),

    ;

    private final String errorCode;
    private final String errorMessage;
}
