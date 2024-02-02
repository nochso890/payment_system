package com.task.payment_system.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BaseStatus {

    SUCCESS("0000","SUCCESS"),
    FAIL("1111","FAIL")
    ;

    private final String code;
    private final String message;


}
