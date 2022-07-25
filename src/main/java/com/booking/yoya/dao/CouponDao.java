package com.booking.yoya.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.Coupon;

@Repository
public interface CouponDao extends JpaRepository<Coupon, Long> {

	public static final String FINDBYCOUPONNAME = "SELECT * from coupon where coupon_name=:couponName";

	@Query(value = FINDBYCOUPONNAME, nativeQuery = true)
	Optional<Coupon> findByCouponName(String couponName);

}
