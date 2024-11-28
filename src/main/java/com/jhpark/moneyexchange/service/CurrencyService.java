package com.jhpark.moneyexchange.service;

import com.jhpark.moneyexchange.dto.CurrencyRequestDto;
import com.jhpark.moneyexchange.dto.CurrencyResponseDto;
import com.jhpark.moneyexchange.entity.Currency;
import com.jhpark.moneyexchange.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;


    public CurrencyResponseDto save(CurrencyRequestDto currencyRequestDto) {
        Currency savedCurrency = currencyRepository.save(currencyRequestDto.toEntity());
        return new CurrencyResponseDto(savedCurrency);
    }

    public List<CurrencyResponseDto> findAll() {
        return currencyRepository.findAll().stream().map(CurrencyResponseDto::toDto).toList();
    }

    public CurrencyResponseDto findById(Long id) {
        Currency findCurrency = currencyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("통화를 찾을 수 없습니다."));
        return new CurrencyResponseDto(findCurrency);
    }
}
