package com.jhpark.moneyexchange.dto;

import com.jhpark.moneyexchange.ExchangeRequestStatus;
import com.jhpark.moneyexchange.entity.UserCurrency;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class ExchangeResponseDto {
    private final Long id;
    private final String name;
    private final String currencyName;
    private final BigDecimal amountInKrw;
    private final BigDecimal amountAfterExchange;
    private final ExchangeRequestStatus exchangeRequestStatus;

    public ExchangeResponseDto(UserCurrency userCurrency) {
        this.id = userCurrency.getId();
        this.name = userCurrency.getUser().getName();
        this.currencyName = userCurrency.getCurrency().getCurrencyName();
        this.amountInKrw = userCurrency.getAmountInKrw();
        this.amountAfterExchange = userCurrency.getAmountAfterExchange();
        this.exchangeRequestStatus = userCurrency.getExchangeRequestStatus();
    }

    public static ExchangeResponseDto toDto(UserCurrency userCurrency) {
        return new ExchangeResponseDto(
                userCurrency.getId(),
                userCurrency.getUser().getName(),
                userCurrency.getCurrency().getCurrencyName(),
                userCurrency.getAmountInKrw(),
                userCurrency.getAmountAfterExchange(),
                userCurrency.getExchangeRequestStatus()
        );
    }
}
