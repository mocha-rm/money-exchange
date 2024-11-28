package com.jhpark.moneyexchange.service;

import com.jhpark.moneyexchange.ExchangeRequestStatus;
import com.jhpark.moneyexchange.dto.ExchangeRequestDto;
import com.jhpark.moneyexchange.dto.ExchangeResponseDto;
import com.jhpark.moneyexchange.dto.ExchangeStatusRequestDto;
import com.jhpark.moneyexchange.entity.Currency;
import com.jhpark.moneyexchange.entity.User;
import com.jhpark.moneyexchange.entity.UserCurrency;
import com.jhpark.moneyexchange.repository.CurrencyRepository;
import com.jhpark.moneyexchange.repository.MoneyExchangeRepository;
import com.jhpark.moneyexchange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MoneyExchangeService {

    private final MoneyExchangeRepository moneyExchangeRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

    public ExchangeResponseDto exchange(Long id, ExchangeRequestDto exchangeRequestDto) {
        //로직 수행
        User findUser = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다.")
        );

        Currency findCurrency = currencyRepository.findByCurrencyName(exchangeRequestDto.getCurrencyName()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 통화를 찾을 수 없습니다.")
        );

        UserCurrency exchangedRequest = new UserCurrency(
                findUser,
                findCurrency,
                exchangeRequestDto.getExchangeAmount(),
                calculateExchange(exchangeRequestDto.getExchangeAmount(), findCurrency.getExchangeRate()),
                ExchangeRequestStatus.NORMAL
        );

        //DB 저장
        moneyExchangeRepository.save(exchangedRequest);
        return new ExchangeResponseDto(exchangedRequest);
    }

    public List<ExchangeResponseDto> findExchangeRequests(Long id) {
        return moneyExchangeRepository.findByUserId(id).stream()
                .map(ExchangeResponseDto::toDto)
                .toList();
    }

    public ExchangeResponseDto patchExchangeRequestStatus(Long id, ExchangeStatusRequestDto exchangeStatusRequestDto) {
        UserCurrency exchangedRequestData = moneyExchangeRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "요청을 찾을 수 없습니다.")
        );

        exchangedRequestData.patchExchangeRequestStatus(exchangeStatusRequestDto.getExchangeRequestStatus());
        moneyExchangeRepository.save(exchangedRequestData);
        return new ExchangeResponseDto(exchangedRequestData);
    }


    // 다른 통화가 추가 될 상황도 고려하기, 매개변수로 어떤 통화로 환전할지 넣는건 어떤지?
    private BigDecimal calculateExchange(BigDecimal amountInKrw, BigDecimal exchangeRate) {
        if (exchangeRate.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("잘못된 환율 입니다.");
        }
        return amountInKrw.divide(exchangeRate, 2, RoundingMode.HALF_UP);
    }
}
