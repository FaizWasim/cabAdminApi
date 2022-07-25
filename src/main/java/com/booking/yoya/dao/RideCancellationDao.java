package com.booking.yoya.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.RideCancellation;

@Repository
public interface RideCancellationDao extends JpaRepository<RideCancellation, Long> {
}
