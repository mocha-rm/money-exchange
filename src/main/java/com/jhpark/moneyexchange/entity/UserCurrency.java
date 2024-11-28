package com.jhpark.moneyexchange.entity;

import com.jhpark.moneyexchange.ExchangeRequestStatus;
import com.jhpark.moneyexchange.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "user_currency")
public class UserCurrency extends BaseEntity { // User 와 Currency 의 중간테이블
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "to_currency_id")
    private Currency currency;

    private BigDecimal amountInKrw;
    private BigDecimal amountAfterExchange;

    private ExchangeRequestStatus exchangeRequestStatus;

    public void patchExchangeRequestStatus(ExchangeRequestStatus status) {
        this.exchangeRequestStatus = status;
    }

    public UserCurrency(User user, Currency currency, BigDecimal amountInKrw, BigDecimal amountAfterExchange, ExchangeRequestStatus exchangeStatus) {
        this.user = user;
        this.currency = currency;
        this.amountInKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
        this.exchangeRequestStatus = exchangeStatus;
    }

    protected UserCurrency() {};
}
