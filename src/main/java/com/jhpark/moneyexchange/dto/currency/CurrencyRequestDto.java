package com.jhpark.moneyexchange.dto.currency;

import com.jhpark.moneyexchange.entity.Currency;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class CurrencyRequestDto {

    @NotBlank
    private final String currencyName;

    @NotNull
    @DecimalMin(value = "1.0", message = "환율이 0일 수 없습니다.")
    @Digits(integer = 10, fraction = 2, message = "2자리 소수만 입력 가능합니다.")
    private final BigDecimal exchangeRate;

    @NotBlank
    private final String symbol;

    public Currency toEntity() {
        return new Currency(this.currencyName, this.exchangeRate, this.symbol);
    }
}
