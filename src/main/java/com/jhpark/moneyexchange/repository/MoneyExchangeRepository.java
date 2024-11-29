package com.jhpark.moneyexchange.repository;

import com.jhpark.moneyexchange.entity.UserCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoneyExchangeRepository extends JpaRepository<UserCurrency, Long> {

    @Query("select uc from UserCurrency uc join fetch uc.user join fetch uc.currency where uc.user.id = :userId")
    List<UserCurrency> findByUserIdWithJoin(@Param("userId") Long userId);

    @Query("select count(uc), coalesce(sum(uc.amountInKrw), 0) from UserCurrency uc where uc.user.id = :userId")
    List<Object[]> findCountAndSumByUserId(@Param("userId") Long userId);
}
