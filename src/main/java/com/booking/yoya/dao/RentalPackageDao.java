package com.booking.yoya.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.RentalPackage;

@Repository
public interface RentalPackageDao extends JpaRepository<RentalPackage, Long> {
}
