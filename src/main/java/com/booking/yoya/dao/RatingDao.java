package com.booking.yoya.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.Rating;

@Repository
public interface RatingDao extends JpaRepository<Rating, Long> {
}
