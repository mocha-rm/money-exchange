package com.jhpark.moneyexchange.controller;

import com.jhpark.moneyexchange.dto.ExchangeRequestDto;
import com.jhpark.moneyexchange.dto.ExchangeResponseDto;
import com.jhpark.moneyexchange.service.MoneyExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exchange")
public class MoneyExchangeController {

//    private final UserService userService;
//    private final CurrencyService currencyService;
    private final MoneyExchangeService moneyExchangeService;

    @PostMapping("/{id}")
    public ResponseEntity<ExchangeResponseDto> exchangeMoney(@PathVariable Long id, @RequestBody ExchangeRequestDto exchangeRequestDto) {
        return new ResponseEntity<>(moneyExchangeService.exchange(id, exchangeRequestDto), HttpStatus.OK);
    }
}
