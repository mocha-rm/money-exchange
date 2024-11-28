package com.jhpark.moneyexchange.service;

import com.jhpark.moneyexchange.dto.currency.CurrencyRequestDto;
import com.jhpark.moneyexchange.dto.currency.CurrencyResponseDto;
import com.jhpark.moneyexchange.entity.Currency;
import com.jhpark.moneyexchange.exception.CustomException;
import com.jhpark.moneyexchange.exception.CustomExceptionCode;
import com.jhpark.moneyexchange.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;


    public CurrencyResponseDto save(CurrencyRequestDto currencyRequestDto) throws CustomException{
        if (currencyRepository.existsByCurrencyName(currencyRequestDto.getCurrencyName())) {
            throw new CustomException(CustomExceptionCode.CURRENCY_DUPLICATED);
        }

        Currency savedCurrency = currencyRepository.save(currencyRequestDto.toEntity());
        return new CurrencyResponseDto(savedCurrency);
    }

    public List<CurrencyResponseDto> findAll() {
        return currencyRepository.findAll().stream().map(CurrencyResponseDto::toDto).toList();
    }

    public CurrencyResponseDto findById(Long id) throws CustomException {
        Currency findCurrency = currencyRepository.findById(id).orElseThrow(() -> new CustomException(CustomExceptionCode.CURRENCY_NOT_FOUND));
        return new CurrencyResponseDto(findCurrency);
    }
}
