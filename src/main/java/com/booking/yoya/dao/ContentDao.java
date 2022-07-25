package com.booking.yoya.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.Content;

@Repository
public interface ContentDao extends JpaRepository<Content, Long> {

}
