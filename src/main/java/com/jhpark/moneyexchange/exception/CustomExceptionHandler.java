package com.jhpark.moneyexchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        String exceptionMessage = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .orElse("Invalid input");

        CustomExceptionResponseDto exceptionResponse = new CustomExceptionResponseDto(HttpStatus.BAD_REQUEST, exceptionMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity handleCustomException(CustomException exception) {
        return ResponseEntity.status(exception.getExceptionCode().getHttpStatus())
                .body(new CustomExceptionResponseDto(exception.getExceptionCode().getHttpStatus(), exception.getExceptionCode().getMessage()));
    }
}
