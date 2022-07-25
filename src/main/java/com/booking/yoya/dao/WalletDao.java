package com.booking.yoya.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.Wallet;

@Repository
public interface WalletDao extends JpaRepository<Wallet, Long> {

	public static final String FINDBYCUSTOMERID = "SELECT * from wallet where wallet_customer_id=:customerId";

	public static final String FINDBYDRIVERID = "SELECT * from wallet where wallet_drivr_id=:driverId";

	@Query(value = FINDBYCUSTOMERID, nativeQuery = true)
	List<Wallet> findByCustomerId(Long customerId);

	@Query(value = FINDBYDRIVERID, nativeQuery = true)
	List<Wallet> findByDriverId(Long driverId);

}
