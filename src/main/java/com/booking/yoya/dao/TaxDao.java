package com.booking.yoya.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.Tax;

@Repository
public interface TaxDao extends JpaRepository<Tax, Long> {

	public static final String FINDBYTAXNAME = "SELECT * from tax where tax_name=:taxName";

	@Query(value = FINDBYTAXNAME, nativeQuery = true)
	Optional<Tax> findByTaxName(String taxName);

}
