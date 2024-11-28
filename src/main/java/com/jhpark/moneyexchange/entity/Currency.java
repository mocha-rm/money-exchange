package com.jhpark.moneyexchange.entity;

import com.jhpark.moneyexchange.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "currency")
public class Currency extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currencyName;
    private BigDecimal exchangeRate;
    private String symbol;

//    @OneToMany(mappedBy = "user_currency")
//    private final List<UserCurrency> userCurrencies = new ArrayList<>();

    public Currency(String currencyName, BigDecimal exchangeRate, String symbol) {
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
        this.symbol = symbol;
    }

    protected Currency() {};
}
