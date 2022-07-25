package com.booking.yoya.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.City;
import com.booking.yoya.model.State;
import com.booking.yoya.model.Vehicle;

@Repository
public interface VehicleDao extends JpaRepository<Vehicle, Long> {

	public static final String FINDBYVEHICLENAME = "SELECT * from vehicle where name=:vehicleName";

	@Query(value = FINDBYVEHICLENAME, nativeQuery = true)
	Optional<Vehicle> findByVehicleName(String vehicleName);

}
