package com.jhpark.moneyexchange.repository;

import com.jhpark.moneyexchange.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Optional<Currency> findByCurrencyName(String currencyName);

    boolean existsByCurrencyName(String currencyName);
}
