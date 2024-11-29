package com.jhpark.moneyexchange.dto.exchange;

import com.jhpark.moneyexchange.ExchangeRequestStatus;
import com.jhpark.moneyexchange.entity.UserCurrency;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ExchangeResponseDto {
    private final Long id;
    private final String name;
    private final String currencyName;
    private final BigDecimal amountInKrw;
    private final BigDecimal amountAfterExchange;
    private final ExchangeRequestStatus exchangeRequestStatus;
    private final LocalDateTime createdDate;
    private final LocalDateTime modDate;

    public ExchangeResponseDto(UserCurrency userCurrency) {
        this.id = userCurrency.getId();
        this.name = userCurrency.getUser().getName();
        this.currencyName = userCurrency.getCurrency().getCurrencyName();
        this.amountInKrw = userCurrency.getAmountInKrw();
        this.amountAfterExchange = userCurrency.getAmountAfterExchange();
        this.exchangeRequestStatus = userCurrency.getExchangeRequestStatus();
        this.createdDate = userCurrency.getCreatedDate();
        this.modDate = userCurrency.getModDate();
    }

    public static ExchangeResponseDto toDto(UserCurrency userCurrency) {
        return new ExchangeResponseDto(
                userCurrency.getId(),
                userCurrency.getUser().getName(),
                userCurrency.getCurrency().getCurrencyName(),
                userCurrency.getAmountInKrw(),
                userCurrency.getAmountAfterExchange(),
                userCurrency.getExchangeRequestStatus(),
                userCurrency.getCreatedDate(),
                userCurrency.getModDate()
        );
    }
}
