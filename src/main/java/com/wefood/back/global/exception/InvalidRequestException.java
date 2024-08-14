package com.wefood.back.global.exception;

import org.springframework.validation.BindingResult;

/**
 * class: InvalidRequestException.
 *
 * @author JBumLee
 * @version 2024/08/11
 */
public class InvalidRequestException extends RuntimeException {

    private final BindingResult result;

    public InvalidRequestException(BindingResult result) {
        super("Invalid request data");
        this.result = result;
    }

    @Override
    public String getMessage() {
        return result.getAllErrors().toString(); // 에러 메시지 형식을 커스터마이즈할 수 있습니다.
    }
}

