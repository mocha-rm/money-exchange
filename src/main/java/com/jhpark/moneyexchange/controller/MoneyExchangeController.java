package com.jhpark.moneyexchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exchange")
public class MoneyExchangeController {

    @PostMapping
    public ResponseEntity<?> exchangeMoney() {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
