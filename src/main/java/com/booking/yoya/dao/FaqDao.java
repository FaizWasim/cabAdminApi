package com.booking.yoya.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.Faq;

@Repository
public interface FaqDao extends JpaRepository<Faq, Long> {

}
