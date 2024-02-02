package com.task.payment_system.payment.exception;

import com.task.payment_system.common.base.BaseException;
import com.task.payment_system.payment.enums.PaymentError;

public class PaymentException extends BaseException {

    public PaymentException(PaymentError status) {
        super(status.getErrorCode(), status.getErrorMessage());
    }
}
