package com.jhpark.moneyexchange.dto.exchange;

import com.jhpark.moneyexchange.ExchangeRequestStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ExchangeStatusRequestDto {

    @NotNull
    private final ExchangeRequestStatus exchangeRequestStatus;
}
