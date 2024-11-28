package com.jhpark.moneyexchange.entity;

import com.jhpark.moneyexchange.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;


@Getter
@Entity
@Table(name = "currency")
public class Currency extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String currencyName;

    @Column(nullable = false)
    private BigDecimal exchangeRate;

    @Column(nullable = false, length = 3)
    private String symbol;

    public Currency(String currencyName, BigDecimal exchangeRate, String symbol) {
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
        this.symbol = symbol;
    }

    protected Currency() {};
}
