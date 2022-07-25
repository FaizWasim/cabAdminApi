package com.booking.yoya.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.VehicleOutstationPrice;

@Repository
public interface VehicleOutStationPriceDao extends JpaRepository<VehicleOutstationPrice, Long> {
}
