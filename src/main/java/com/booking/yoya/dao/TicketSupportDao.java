package com.booking.yoya.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.TicketSupport;

@Repository
public interface TicketSupportDao extends JpaRepository<TicketSupport, Long> {

//	public static final String FINDBYCITYNAME = "SELECT * from city where city_name=:cityName";
//
//	@Query(value = FINDBYCITYNAME, nativeQuery = true)
//	Optional<City> findByCityName(String cityName);

}
