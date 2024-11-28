package com.jhpark.moneyexchange.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class ExchangeRequestDto {

    @NotNull
    @DecimalMin(value = "1000.00", message = "환전은 1000원 이상 부터 가능합니다.")
    @DecimalMax(value = "10000000", message = "최대 1천만원 까지 지원합니다.")
    private final BigDecimal exchangeAmount;

    @NotBlank
    private final String currencyName;
}
