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
    @Size(min = 2, max = 10, message = "통화 이름은 최소 2자에서 최대 10자 사이로 입력해주세요.")
    private final String currencyName;

    @NotNull
    @DecimalMin(value = "1.0", message = "환율이 0일 수 없습니다.")
    @Digits(integer = 10, fraction = 2, message = "2자리 소수만 입력 가능합니다.")
    private final BigDecimal exchangeRate;

    @NotBlank
    @Size(min = 1, max = 3, message = "심볼은 최소 1자에서 최대 3자 사이로 입력해주세요.")
    private final String symbol;

    public Currency toEntity() {
        return new Currency(this.currencyName, this.exchangeRate, this.symbol);
    }
}
