package com.jhpark.moneyexchange.dto;

import com.jhpark.moneyexchange.entity.Currency;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class CurrencyResponseDto {
    private final Long id;
    private final String currencyName;
    private final BigDecimal exchangeRate;
    private final String symbol;

    public CurrencyResponseDto(Currency currency) {
        this.id = currency.getId();
        this.currencyName = currency.getCurrencyName();
        this.exchangeRate = currency.getExchangeRate();
        this.symbol = currency.getSymbol();
    }

    public static CurrencyResponseDto toDto(Currency currency) {
        return new CurrencyResponseDto(
                currency.getId(),
                currency.getCurrencyName(),
                currency.getExchangeRate(),
                currency.getSymbol()
        );
    }
}
