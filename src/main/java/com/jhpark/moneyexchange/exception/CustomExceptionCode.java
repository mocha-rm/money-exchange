package com.jhpark.moneyexchange.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomExceptionCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    CURRENCY_NOT_FOUND(HttpStatus.NOT_FOUND, "통화를 찾을 수 없습니다."),
    CURRENCY_DUPLICATED(HttpStatus.BAD_REQUEST, "이미 같은 통화가 존재합니다."),
    EXCHANGE_REQUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 요청을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
