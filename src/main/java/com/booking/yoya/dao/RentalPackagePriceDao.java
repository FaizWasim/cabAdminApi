package com.booking.yoya.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.RentalPackagePrice;

@Repository
public interface RentalPackagePriceDao extends JpaRepository<RentalPackagePrice, Long> {
}
