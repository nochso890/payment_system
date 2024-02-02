package com.task.payment_system.common.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    public BaseException(BaseStatus status) {
        this.errorCode = status.getCode();
        this.errorMessage = status.getMessage();
    }

}
