package com.jhpark.moneyexchange.dto;

import com.jhpark.moneyexchange.entity.Currency;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class CurrencyRequestDto {
    private final String currencyName;
    private final BigDecimal exchangeRate;
    private final String symbol;

    public Currency toEntity() {
        return new Currency(this.currencyName, this.exchangeRate, this.symbol);
    }
}
