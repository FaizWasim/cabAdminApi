package com.booking.yoya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.yoya.dto.CouponDto;
import com.booking.yoya.model.Coupon;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.CouponService;

@RestController
@RequestMapping("api/v1/coupon")
public class CouponController {
	@Autowired
	private CouponService couponService;

	@PostMapping(value = "create")
	public ServiceResponse<?> createCoupon(@RequestBody Coupon coupon) {
		return new ServiceResponse<BaseMessageResponse>(couponService.createNewCoupon(coupon));
	}

	@GetMapping(value = "list")
//	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> countryList() {
		return new ServiceResponse<BaseMessageResponse>(couponService.getAllCoupons());
	}

	@DeleteMapping(value = "{couponId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> deleteCoupon(@PathVariable Long couponId) {
		return new ServiceResponse<BaseMessageResponse>(couponService.deleteCoupon(couponId));
	}

	@GetMapping(value = "{couponId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> couponById(@PathVariable Long couponId) {
		return new ServiceResponse<BaseMessageResponse>(couponService.couponById(couponId));
	}

	@PutMapping(value = "{couponId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> updateCoupon(@PathVariable Long couponId, @RequestBody CouponDto coupon) {
		coupon.setId(couponId);
		return new ServiceResponse<BaseMessageResponse>(couponService.updateCoupon(coupon));
	}
}
