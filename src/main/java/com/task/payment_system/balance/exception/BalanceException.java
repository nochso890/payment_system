package com.task.payment_system.balance.exception;

import com.task.payment_system.balance.enums.BalanceError;
import com.task.payment_system.common.base.BaseException;

public class BalanceException extends BaseException {

    public BalanceException(BalanceError status) {
        super(status.getErrorCode(), status.getErrorMessage());
    }

}
