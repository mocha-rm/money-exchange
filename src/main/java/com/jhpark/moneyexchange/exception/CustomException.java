package com.jhpark.moneyexchange.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends Exception {
    private final CustomExceptionCode exceptionCode;
}
