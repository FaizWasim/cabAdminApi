package com.booking.yoya.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.booking.yoya.constant.NumbersConstant;
import com.booking.yoya.constant.StringsConstant;
import com.booking.yoya.dao.CityDao;
import com.booking.yoya.dao.CountryDao;
import com.booking.yoya.dao.CouponDao;
import com.booking.yoya.dao.ProcessDao;
import com.booking.yoya.dao.StateDao;
import com.booking.yoya.dao.VehicleDao;
import com.booking.yoya.dto.CouponDto;
import com.booking.yoya.model.City;
import com.booking.yoya.model.Country;
import com.booking.yoya.model.Coupon;
import com.booking.yoya.model.Process;
import com.booking.yoya.model.State;
import com.booking.yoya.model.Vehicle;
import com.booking.yoya.response.BaseMessageResponse;

@Service
public class CouponService {

	@Autowired
	private CouponDao couponDao;

	@Autowired
	private CountryDao countryDao;

	@Autowired
	private StateDao stateDao;

	@Autowired
	private CityDao cityDao;

	@Autowired
	private ProcessDao processDao;

	@Autowired
	private VehicleDao vehicleDao;

	public BaseMessageResponse<Object> createNewCoupon(Coupon coupon) {
		Optional<Coupon> couponName = couponDao.findByCouponName(coupon.getCouponName());

		if (couponName.isPresent()) {
			return BaseMessageResponse.builder().message("coupon already present").status(true).data(couponName)
					.build();

		} else {
			Coupon savedCoupon = couponDao.save(coupon);
			return BaseMessageResponse.builder().message("success").status(true).data(savedCoupon).build();
		}
	}

	public BaseMessageResponse<Object> getAllCoupons() {

		List<Coupon> couponList = couponDao.findAll();
		if (couponList.isEmpty()) {
			return BaseMessageResponse.builder().message("success").status(false).data(couponList).build();
		} else {
			List<CouponDto> couponDtoList = couponList.stream().map(new Function<Coupon, CouponDto>() {
				@Override
				public CouponDto apply(Coupon s) {
					CouponDto sDto = new CouponDto();

					Optional<Country> country = countryDao.findById(s.getCountryId());
					Optional<State> state = stateDao.findById(s.getStateId());
					Optional<City> city = cityDao.findById(s.getCityId());
					Optional<Process> process = processDao.findById(s.getProcessId());
					Optional<Vehicle> vehicle = vehicleDao.findById(s.getVehicleId());

					sDto.setId(s.getId());
					sDto.setCountryId(s.getCountryId());
					sDto.setStateId(s.getStateId());
					sDto.setCityId(s.getCityId());
					sDto.setProcessId(s.getProcessId());
					sDto.setVehicleId(s.getVehicleId());
					sDto.setUserId(s.getUserId());

					if (!country.isPresent())
						sDto.setCountryName(StringsConstant.EMPTY.value);
					else
						sDto.setCountryName(country.get().getCountryName());

					if (!state.isPresent())
						sDto.setStateName(StringsConstant.EMPTY.value);
					else
						sDto.setStateName(state.get().getStateName());

					if (!city.isPresent())
						sDto.setCityName(StringsConstant.EMPTY.value);
					else
						sDto.setCityName(city.get().getCityName());

					if (!process.isPresent())
						sDto.setProcessName(StringsConstant.EMPTY.value);
					else
						sDto.setProcessName(process.get().getName());

					if (!vehicle.isPresent())
						sDto.setVehicleName(StringsConstant.EMPTY.value);
					else
						sDto.setVehicleName(vehicle.get().getName());

					sDto.setUserName(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName()));

					sDto.setCouponName(s.getCouponName());
					sDto.setCouponCode(s.getCouponCode());
					sDto.setIsAutoApply(s.getIsAutoApply());
					sDto.setDiscountType(s.getDiscountType());
					sDto.setCouponType(s.getCouponType());
					sDto.setMaxUserUses(s.getMaxUserUses());
					sDto.setMaxAmountOfDiscount(s.getMaxAmountOfDiscount());
					sDto.setMinAmount(s.getMinAmount());
					sDto.setStartDate(s.getStartDate());
					sDto.setEndDate(s.getEndDate());
					sDto.setAmount(s.getAmount());
					if (s.getIsActive().equals(true))
						sDto.setIsActive(NumbersConstant.ONE.value);
					else
						sDto.setIsActive(NumbersConstant.ZERO.value);

					return sDto;
				}
			}).collect(Collectors.toList());

			return BaseMessageResponse.builder().message("success").status(true).data(couponDtoList).build();

		}

	}

	public BaseMessageResponse<Object> deleteCoupon(Long couponId) {
		Optional<Coupon> coupon = couponDao.findById(couponId);

		if (!coupon.isPresent()) {
			return BaseMessageResponse.builder().message("coupon not present").status(false).data(coupon).build();

		} else {
			couponDao.deleteById(coupon.get().getId());
			return BaseMessageResponse.builder().message("success").status(true).data(coupon).build();
		}
	}

	public BaseMessageResponse<Object> updateCoupon(CouponDto coupon) {
		Coupon oldCoupon = couponDao.findById(coupon.getId()).get();

		if (coupon.getId() != null) {
			oldCoupon.setCountryId(coupon.getCountryId());
			oldCoupon.setStateId(coupon.getStateId());

			oldCoupon.setCityId(coupon.getCityId());
			oldCoupon.setProcessId(coupon.getProcessId());
			oldCoupon.setVehicleId(coupon.getVehicleId());
			oldCoupon.setUserId(coupon.getUserId());
			oldCoupon.setCouponName(coupon.getCouponName());
			oldCoupon.setCouponCode(coupon.getCouponCode());
			oldCoupon.setIsAutoApply(coupon.getIsAutoApply());
			oldCoupon.setDiscountType(coupon.getDiscountType());
			oldCoupon.setCouponType(coupon.getCouponType());
			oldCoupon.setMaxUserUses(coupon.getMaxUserUses());
			oldCoupon.setMaxAmountOfDiscount(coupon.getMaxAmountOfDiscount());
			oldCoupon.setMinAmount(coupon.getMinAmount());
			oldCoupon.setStartDate(coupon.getStartDate());
			oldCoupon.setEndDate(coupon.getEndDate());
			if (coupon.getIsActive() == NumbersConstant.ONE.value)
				oldCoupon.setIsActive(true);
			else
				oldCoupon.setIsActive(false);

			Coupon savedCoupon = couponDao.save(oldCoupon);
			return BaseMessageResponse.builder().message("success").status(true).data(savedCoupon).build();
		} else {
			return BaseMessageResponse.builder().message("coupon not present").status(false).data(coupon).build();
		}
	}

	public BaseMessageResponse<Object> couponById(Long couponId) {
		Optional<Coupon> coupon = couponDao.findById(couponId);

		if (!coupon.isPresent()) {
			return BaseMessageResponse.builder().message("coupon not present").status(false).data(coupon).build();

		} else {
			CouponDto sDto = new CouponDto();

			Optional<Country> country = countryDao.findById(coupon.get().getCountryId());
			Optional<State> state = stateDao.findById(coupon.get().getStateId());
			Optional<City> city = cityDao.findById(coupon.get().getCityId());
			Optional<Process> process = processDao.findById(coupon.get().getProcessId());
			Optional<Vehicle> vehicle = vehicleDao.findById(coupon.get().getVehicleId());

			sDto.setId(coupon.get().getId());
			sDto.setCountryId(coupon.get().getCountryId());
			sDto.setStateId(coupon.get().getStateId());

			sDto.setCityId(coupon.get().getCityId());
			sDto.setProcessId(coupon.get().getProcessId());
			sDto.setVehicleId(coupon.get().getVehicleId());
			sDto.setUserId(coupon.get().getUserId());

			if (!country.isPresent())
				sDto.setCountryName(StringsConstant.EMPTY.value);
			else
				sDto.setCountryName(country.get().getCountryName());

			if (!state.isPresent())
				sDto.setStateName(StringsConstant.EMPTY.value);
			else
				sDto.setStateName(state.get().getStateName());

			if (!city.isPresent())
				sDto.setCityName(StringsConstant.EMPTY.value);
			else
				sDto.setCityName(city.get().getCityName());

			if (!process.isPresent())
				sDto.setProcessName(StringsConstant.EMPTY.value);
			else
				sDto.setProcessName(process.get().getName());

			if (!vehicle.isPresent())
				sDto.setVehicleName(StringsConstant.EMPTY.value);
			else
				sDto.setVehicleName(vehicle.get().getName());

			sDto.setUserName(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName()));

			sDto.setCouponName(coupon.get().getCouponName());
			sDto.setCouponCode(coupon.get().getCouponCode());
			sDto.setIsAutoApply(coupon.get().getIsAutoApply());
			sDto.setDiscountType(coupon.get().getDiscountType());
			sDto.setCouponType(coupon.get().getCouponType());
			sDto.setMaxUserUses(coupon.get().getMaxUserUses());
			sDto.setMaxAmountOfDiscount(coupon.get().getMaxAmountOfDiscount());
			sDto.setMinAmount(coupon.get().getMinAmount());
			sDto.setStartDate(coupon.get().getStartDate());
			sDto.setEndDate(coupon.get().getEndDate());
			sDto.setAmount(coupon.get().getAmount());

			if (coupon.get().getIsActive().equals(true))
				sDto.setIsActive(NumbersConstant.ONE.value);
			else
				sDto.setIsActive(NumbersConstant.ZERO.value);

			return BaseMessageResponse.builder().message("success").status(true).data(sDto).build();
		}
	}

}
