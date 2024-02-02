package com.task.payment_system.balance.exception;

import com.task.payment_system.balance.enums.BalanceError;
import com.task.payment_system.common.BaseException;
import com.task.payment_system.common.BaseStatus;

public class BalanceException extends BaseException {

    public BalanceException(BalanceError status){
        super(status.getErrorCode(),status.getErrorMessage());
    }

}
