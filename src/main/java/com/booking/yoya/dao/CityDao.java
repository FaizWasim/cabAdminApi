package com.booking.yoya.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.City;

@Repository
public interface CityDao extends JpaRepository<City, Long> {

	public static final String FINDBYCITYNAME = "SELECT * from city where city_name=:cityName";

	public static final String FINDBYSTATEID = "SELECT * from city where state_id=:stateId";

	@Query(value = FINDBYCITYNAME, nativeQuery = true)
	Optional<City> findByCityName(String cityName);

	@Query(value = FINDBYSTATEID, nativeQuery = true)
	List<City> findByStateId(Optional<Long> stateId);

}
