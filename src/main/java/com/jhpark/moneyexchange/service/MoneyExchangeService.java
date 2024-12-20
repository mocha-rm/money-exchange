package com.jhpark.moneyexchange.service;

import com.jhpark.moneyexchange.common.ExchangeRequestStatus;
import com.jhpark.moneyexchange.dto.exchange.ExchangeRequestDto;
import com.jhpark.moneyexchange.dto.exchange.ExchangeResponseDto;
import com.jhpark.moneyexchange.dto.exchange.ExchangeStatusRequestDto;
import com.jhpark.moneyexchange.entity.Currency;
import com.jhpark.moneyexchange.entity.User;
import com.jhpark.moneyexchange.entity.UserCurrency;
import com.jhpark.moneyexchange.exception.CustomException;
import com.jhpark.moneyexchange.exception.CustomExceptionCode;
import com.jhpark.moneyexchange.repository.CurrencyRepository;
import com.jhpark.moneyexchange.repository.MoneyExchangeRepository;
import com.jhpark.moneyexchange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MoneyExchangeService {

    private final MoneyExchangeRepository moneyExchangeRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

    @Transactional
    public ExchangeResponseDto exchange(Long id, ExchangeRequestDto exchangeRequestDto) throws CustomException {
        //로직 수행
        User findUser = userRepository.findById(id).orElseThrow(() ->
                new CustomException(CustomExceptionCode.USER_NOT_FOUND)
        );

        Currency findCurrency = currencyRepository.findByCurrencyName(exchangeRequestDto.getCurrencyName()).orElseThrow(() ->
                new CustomException(CustomExceptionCode.CURRENCY_NOT_FOUND)
        );

        UserCurrency exchangedRequest = new UserCurrency(
                findUser,
                findCurrency,
                exchangeRequestDto.getExchangeAmount(),
                calculateExchange(exchangeRequestDto.getExchangeAmount(), findCurrency.getExchangeRate(), exchangeRequestDto.getCurrencyName()),
                ExchangeRequestStatus.NORMAL
        );

        //DB 저장
        moneyExchangeRepository.save(exchangedRequest);
        return new ExchangeResponseDto(exchangedRequest);
    }

    public List<ExchangeResponseDto> findExchangeRequests(Long id) {
        return moneyExchangeRepository.findByUserIdWithJoin(id).stream()
                .map(ExchangeResponseDto::toDto)
                .toList();
    }

    public Map<String, Object> getCountAndSum(Long userId) {
        List<Object[]> resultList = moneyExchangeRepository.findCountAndSumByUserId(userId);

        if (resultList.isEmpty()) {
            return Map.of("sum", BigDecimal.ZERO, "count", 0L);
        }

        Object[] result = resultList.get(0);
        Long count = ((Number) result[0]).longValue();
        BigDecimal sum = (BigDecimal) result[1];

        return Map.of(
                "totalAmountInKrw", sum,
                "count", count
        );
    }

    @Transactional
    public ExchangeResponseDto patchExchangeRequestStatus(Long id, ExchangeStatusRequestDto exchangeStatusRequestDto) throws CustomException {
        UserCurrency exchangedRequestData = moneyExchangeRepository.findById(id).orElseThrow(() ->
                new CustomException(CustomExceptionCode.EXCHANGE_REQUEST_NOT_FOUND)
        );

        if (exchangedRequestData.getExchangeRequestStatus().equals(exchangeStatusRequestDto.getExchangeRequestStatus())) {
            throw new CustomException(CustomExceptionCode.REQUEST_DUPLICATED);
        }

        exchangedRequestData.patchExchangeRequestStatus(exchangeStatusRequestDto.getExchangeRequestStatus());
        moneyExchangeRepository.save(exchangedRequestData);
        return new ExchangeResponseDto(exchangedRequestData);
    }

    /*
     * 환율 계산
     * 1달러, 1엔, 1위안 기준
     * 달러) 1,0000원 / 1395.00
     * 엔) 1,0000원 / 9.30
     * 위안) 1,000원 / 192.30
     */
    private BigDecimal calculateExchange(BigDecimal amountInKrw, BigDecimal exchangeRate, String currencyName) {
        if (exchangeRate.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ArithmeticException("잘못된 환율 입니다.");
        }

        return switch (currencyName) {
            case "USD", "CNY" ->
                    amountInKrw.divide(exchangeRate, 2, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
            case "Yen" -> amountInKrw.divide(exchangeRate, 0, RoundingMode.HALF_UP);
            default -> throw new IllegalArgumentException("지원하지 않는 통화입니다.");
        };
    }
}