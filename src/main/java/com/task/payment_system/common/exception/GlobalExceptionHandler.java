package com.task.payment_system.common.exception;

import com.task.payment_system.common.base.BaseException;
import com.task.payment_system.common.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<BaseResponse<Void>> baseExceptionHandler(BaseException exception) {
        log.error("BASE_EXCEPTION::", exception);
        return new ResponseEntity<>(new BaseResponse<>(exception), HttpStatus.OK);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Void>> badRequestExceptionHandler(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
            .toList();
        log.error("BADREQUEST_EXCEPTION::", ex);
        return new ResponseEntity<>(new BaseResponse<>(HttpStatus.BAD_REQUEST, errors), HttpStatus.BAD_REQUEST);
    }
}
