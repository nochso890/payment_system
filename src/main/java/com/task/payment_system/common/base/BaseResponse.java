package com.task.payment_system.common.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@Getter
public class BaseResponse<T> {

    private final String serverCode;
    private final String serverMessage;
    private T data;

    public BaseResponse() {
        this.serverCode = BaseStatus.SUCCESS.getCode();
        this.serverMessage = BaseStatus.SUCCESS.getMessage();
    }

    public BaseResponse(String serverCode, String serverMessage) {
        this.serverCode = serverCode;
        this.serverMessage = serverMessage;
    }

    public BaseResponse(T object) {
        this();
        this.data = object;
    }

    public BaseResponse(BaseException exception) {
        this(exception.getErrorCode(), exception.getErrorMessage());
    }

    public BaseResponse(HttpStatus httpStatus, List<String> errors) {
        this(httpStatus.name(), convertErrorsByJsonString(errors));
    }

    private static String convertErrorsByJsonString(List<String> errors) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            var jsonStr = objectMapper.writeValueAsString(errors);
            return jsonStr.replaceAll("[\"\\[\\]]", "").replace(",", "::");
        } catch (JsonProcessingException e) {
            log.error("JSON_PROCeSSING_EXCPETION::", e);
            throw new BaseException(BaseStatus.FAIL);
        }
    }

}
