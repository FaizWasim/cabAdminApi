package com.booking.yoya.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.State;

@Repository
public interface StateDao extends JpaRepository<State, Long> {

	public static final String FINDBYSTATENAME = "SELECT * from state where state_name=:stateName";

	public static final String FINDBYCOUNTRYID = "SELECT * from state where country_id=:countryId";

	@Query(value = FINDBYSTATENAME, nativeQuery = true)
	Optional<State> findByStateName(String stateName);

	@Query(value = FINDBYCOUNTRYID, nativeQuery = true)
	List<State> findByCountryId(Long countryId);

}
