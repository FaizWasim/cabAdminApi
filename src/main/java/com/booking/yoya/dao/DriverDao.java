package com.booking.yoya.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.Driver;

@Repository
public interface DriverDao extends JpaRepository<Driver, Long> {

//	public static final String FINDBYCITYNAME = "SELECT * from city where city_name=:cityName";
//
//	public static final String FINDBYSTATEID = "SELECT * from city where state_id=:stateId";
//
//	@Query(value = FINDBYCITYNAME, nativeQuery = true)
//	Optional<City> findByCityName(String cityName);
//
//	@Query(value = FINDBYSTATEID, nativeQuery = true)
//	List<City> findByStateId(Optional<Long> stateId);

}
