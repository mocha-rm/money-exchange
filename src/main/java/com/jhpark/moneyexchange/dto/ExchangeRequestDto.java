package com.jhpark.moneyexchange.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class ExchangeRequestDto {
    private final BigDecimal exchangeAmount;
    private final String currencyName;
}
