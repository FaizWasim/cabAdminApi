package com.booking.yoya.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.VehicleNormalPrice;

@Repository
public interface VehicleNormalPriceDao extends JpaRepository<VehicleNormalPrice, Long> {
}
