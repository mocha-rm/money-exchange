package com.jhpark.moneyexchange.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class CustomExceptionResponseDto {
    private final HttpStatus httpStatus;
    private final String message;
}
