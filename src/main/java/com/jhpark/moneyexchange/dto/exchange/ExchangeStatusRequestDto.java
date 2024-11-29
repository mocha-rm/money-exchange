package com.jhpark.moneyexchange.dto.exchange;

import com.jhpark.moneyexchange.common.ExchangeRequestStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ExchangeStatusRequestDto {

    @NotNull
    private final ExchangeRequestStatus exchangeRequestStatus;
    private final LocalDateTime createdDate;
    private final LocalDateTime modDate;
}
