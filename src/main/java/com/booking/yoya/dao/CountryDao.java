package com.booking.yoya.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.Country;

@Repository
public interface CountryDao extends JpaRepository<Country, Long> {

	public static final String FINDBYCOUNTRYNAME = "SELECT * from country where country_name=:countryName";

	
	@Query(value = FINDBYCOUNTRYNAME, nativeQuery = true)
	Optional<Country> findByCountryName(String countryName);

}
