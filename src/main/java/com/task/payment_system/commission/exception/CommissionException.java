package com.task.payment_system.commission.exception;

import com.task.payment_system.commission.enums.CommissionError;
import com.task.payment_system.common.base.BaseException;

public class CommissionException extends BaseException {

    public CommissionException(CommissionError commissionError) {
        super(commissionError.getErrorCode(), commissionError.getErrorMessage());
    }
}
