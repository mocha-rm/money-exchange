package com.jhpark.moneyexchange.controller;

import com.jhpark.moneyexchange.dto.ExchangeRequestDto;
import com.jhpark.moneyexchange.dto.ExchangeResponseDto;
import com.jhpark.moneyexchange.dto.ExchangeStatusRequestDto;
import com.jhpark.moneyexchange.service.MoneyExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exchange")
public class MoneyExchangeController {

    private final MoneyExchangeService moneyExchangeService;

    @PostMapping("/{id}")
    public ResponseEntity<ExchangeResponseDto> sendExchangeRequest(@PathVariable Long id, @RequestBody ExchangeRequestDto exchangeRequestDto) {
        return new ResponseEntity<>(moneyExchangeService.exchange(id, exchangeRequestDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ExchangeResponseDto>> viewExchangeRequests(@PathVariable Long id) {
        return new ResponseEntity<>(moneyExchangeService.findExchangeRequests(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ExchangeResponseDto> patchExchangeRequestStatus(@PathVariable Long id, @RequestBody ExchangeStatusRequestDto exchangeStatusRequestDto) {
        return new ResponseEntity<>(moneyExchangeService.patchExchangeRequestStatus(id, exchangeStatusRequestDto), HttpStatus.OK);
    }
}
