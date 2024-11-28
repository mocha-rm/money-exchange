package com.jhpark.moneyexchange.dto;

import com.jhpark.moneyexchange.ExchangeRequestStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ExchangeStatusRequestDto {
    private final ExchangeRequestStatus exchangeRequestStatus;
}
