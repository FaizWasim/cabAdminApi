package com.booking.yoya.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.Currency;

@Repository
public interface CurrencyDao extends JpaRepository<Currency, Long> {

	public static final String FINDBYCURRENCYNAME = "SELECT * from currency where name=:currencyName";

	@Query(value = FINDBYCURRENCYNAME, nativeQuery = true)
	Optional<Currency> findByCurrencyName(String currencyName);

}
