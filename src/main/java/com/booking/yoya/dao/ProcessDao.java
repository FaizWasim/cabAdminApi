package com.booking.yoya.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.Process;

@Repository
public interface ProcessDao extends JpaRepository<Process, Long> {

	public static final String FINDBYPROCESSNAME = "SELECT * from process where name=:processName";

	@Query(value = FINDBYPROCESSNAME, nativeQuery = true)
	Optional<Process> findByProcessName(String processName);

}
