package com.task.payment_system.commission.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommissionError {

    NOT_FOUND_COMISSION_DATA("700","수수료 정보를 찾을 수 없습니다.")
    ;

    private final String errorCode;
    private final String errorMessage;
}
