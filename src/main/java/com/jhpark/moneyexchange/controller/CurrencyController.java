package com.jhpark.moneyexchange.controller;

import com.jhpark.moneyexchange.dto.currency.CurrencyRequestDto;
import com.jhpark.moneyexchange.dto.currency.CurrencyResponseDto;
import com.jhpark.moneyexchange.exception.CustomException;
import com.jhpark.moneyexchange.service.CurrencyService;
import jakarta.validation.Valid;
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

    /**
     * 통화 생성
     * @param currencyRequestDto (통화 생성에 필요한 요청 정보 전달)
     * @return (CurrencyResponseDto)
     * @throws CustomException (중복된 통화를 생성할 시 예외 발생)
     */
    @PostMapping
    public ResponseEntity<CurrencyResponseDto> createCurrency(@RequestBody @Valid CurrencyRequestDto currencyRequestDto) throws CustomException {
        return new ResponseEntity<>(currencyService.save(currencyRequestDto), HttpStatus.CREATED);
    }

    /**
     * 통화 전체 조회
     * @return (List<CurrencyResponseDto>)
     */
    @GetMapping
    public ResponseEntity<List<CurrencyResponseDto>> findCurrencies() {
        return ResponseEntity.ok().body(currencyService.findAll());
    }

    /**
     * 통화 선택 조회
     * @param id (통화 ID)
     * @return (CurrencyResponseDto)
     * @throws CustomException (통화를 찾을 수 없으면 예외 발생)
     */
    @GetMapping("/{id}")
    public ResponseEntity<CurrencyResponseDto> findCurrency(@PathVariable Long id) throws CustomException {
        return ResponseEntity.ok().body(currencyService.findById(id));
    }
}
