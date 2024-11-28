package com.jhpark.moneyexchange.repository;

import com.jhpark.moneyexchange.entity.Currency;
import com.jhpark.moneyexchange.entity.User;
import com.jhpark.moneyexchange.entity.UserCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyExchangeRepository extends JpaRepository<UserCurrency, Long> {
    @Query("select uc.user from UserCurrency uc where uc.user.id = :userId")
    User findUserByUserId(@Param("userId") Long userId);

    @Query("select uc.currency from UserCurrency uc where uc.currency.currencyName = :currencyName")
    Currency findCurrencyByCurrencyName(@Param("currencyName") String currencyName);
}
