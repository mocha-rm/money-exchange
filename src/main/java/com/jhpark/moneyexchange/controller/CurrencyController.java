package com.jhpark.moneyexchange.controller;

import com.jhpark.moneyexchange.dto.CurrencyRequestDto;
import com.jhpark.moneyexchange.dto.CurrencyResponseDto;
import com.jhpark.moneyexchange.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    @PostMapping
    public ResponseEntity<CurrencyResponseDto> createCurrency(@RequestBody CurrencyRequestDto currencyRequestDto) {
        return new ResponseEntity<>(currencyService.save(currencyRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CurrencyResponseDto>> findCurrencies() {
        return ResponseEntity.ok().body(currencyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyResponseDto> findCurrency(@PathVariable Long id) {
        return ResponseEntity.ok().body(currencyService.findById(id));
    }
}