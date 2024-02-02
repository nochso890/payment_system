package com.task.payment_system.commission.exception;

import com.task.payment_system.commission.enums.CommissionError;
import com.task.payment_system.common.base.BaseException;
import com.task.payment_system.common.base.BaseStatus;

public class CommissionException extends BaseException {

    public CommissionException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public CommissionException(BaseStatus status) {
        super(status);
    }

    public CommissionException(CommissionError commissionError) {
        super(commissionError.getErrorCode(), commissionError.getErrorMessage());
    }
}
