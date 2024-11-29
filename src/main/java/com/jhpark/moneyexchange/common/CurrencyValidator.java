package com.jhpark.moneyexchange.common;

import com.jhpark.moneyexchange.entity.Currency;
import com.jhpark.moneyexchange.repository.CurrencyRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
public class CurrencyValidator {

    private final BigDecimal limit = new BigDecimal("2000");

    private final CurrencyRepository currencyRepository;

    public CurrencyValidator(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    //@PostConstruct 로 Application 최초 실행 시에만 작동
    @PostConstruct
    public void validateCurrencyRate() {
        List<Currency> currenciesRates = currencyRepository.findAll();
        for (Currency currency : currenciesRates) {
            if (currency.getExchangeRate().compareTo(BigDecimal.ZERO) <= 0 || currency.getExchangeRate().compareTo(limit) > 0) {
                log.error("환율이 정상 값을 벗어났습니다. 확인해주세요");
            }
        }
    }
}
