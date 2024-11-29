package com.jhpark.moneyexchange.controller;

import com.jhpark.moneyexchange.dto.exchange.ExchangeRequestDto;
import com.jhpark.moneyexchange.dto.exchange.ExchangeResponseDto;
import com.jhpark.moneyexchange.dto.exchange.ExchangeStatusRequestDto;
import com.jhpark.moneyexchange.exception.CustomException;
import com.jhpark.moneyexchange.service.MoneyExchangeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exchange/{id}")
public class MoneyExchangeController {

    private final MoneyExchangeService moneyExchangeService;

    /**
     * 환전 요청 생성
     * @param id (유저 ID)
     * @param exchangeRequestDto (환전에 필요한 요청 정보 전달)
     * @return (ExchangeResponseDto)
     * @throws CustomException (유저 또는 통화를 찾을 수 없을 시 예외 발생)
     */
    @PostMapping
    public ResponseEntity<ExchangeResponseDto> sendExchangeRequest(
            @PathVariable Long id,
            @RequestBody @Valid ExchangeRequestDto exchangeRequestDto
    ) throws CustomException {
        return new ResponseEntity<>(moneyExchangeService.exchange(id, exchangeRequestDto), HttpStatus.OK);
    }

    /**
     * 특정 유저가 생성한 환전 요청 전부 조회
     * @param id (유저 ID)
     * @return (List<ExchangeResponseDto>)
     */
    @GetMapping
    public ResponseEntity<List<ExchangeResponseDto>> viewExchangeRequests(@PathVariable Long id) {
        return new ResponseEntity<>(moneyExchangeService.findExchangeRequests(id), HttpStatus.OK);
    }

    /**
     * 특정 유저가 요청한 횟수와 총 금액 조회
     * @param id (유저 ID)
     * @return Map<String, Object>
     */
    @GetMapping("/total")
    public ResponseEntity<Map<String, Object>> viewExchangeCountAndTotal(@PathVariable Long id) {
        Map<String, Object> response = moneyExchangeService.getCountAndSum(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 환전 요청 상태 변경하기
     * @param id (환전 요청 ID)
     * @param exchangeStatusRequestDto (요청 상태 변경에 필요한 요청 정보 전달, NORMAL, CANCELLED enum 타입으로 관리)
     * @return ExchangeResponseDto
     * @throws CustomException (요청이 없거나, 중복되었을 경우 예외 발생)
     */
    @PatchMapping
    public ResponseEntity<ExchangeResponseDto> patchExchangeRequestStatus(
            @PathVariable Long id,
            @RequestBody @Valid ExchangeStatusRequestDto exchangeStatusRequestDto
    ) throws CustomException {
        return new ResponseEntity<>(moneyExchangeService.patchExchangeRequestStatus(id, exchangeStatusRequestDto), HttpStatus.OK);
    }
}
