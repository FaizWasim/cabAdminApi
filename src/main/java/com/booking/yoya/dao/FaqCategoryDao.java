package com.booking.yoya.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.FaqCategory;

@Repository
public interface FaqCategoryDao extends JpaRepository<FaqCategory, Long> {

	
}
