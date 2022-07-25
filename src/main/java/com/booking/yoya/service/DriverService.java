package com.booking.yoya.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.yoya.constant.StringsConstant;
import com.booking.yoya.dao.CityDao;
import com.booking.yoya.dao.CountryDao;
import com.booking.yoya.dao.DriverDao;
import com.booking.yoya.dao.StateDao;
import com.booking.yoya.dto.DriverDto;
import com.booking.yoya.model.City;
import com.booking.yoya.model.Country;
import com.booking.yoya.model.Driver;
import com.booking.yoya.model.State;
import com.booking.yoya.response.BaseMessageResponse;

@Service
public class DriverService {

	private static Logger logger = LogManager.getLogger();

	@Autowired
	private DriverDao driverDao;

	@Autowired
	private CountryDao countryDao;

	@Autowired
	private StateDao stateDao;

	@Autowired
	private CityDao cityDao;

	public BaseMessageResponse<Object> getAllDrivers() {
		try {

			logger.info("driver list " + driverDao.findAll());

			List<Driver> driverList = driverDao.findAll();
			System.out.println(driverList);
			if (driverList.isEmpty()) {
				return BaseMessageResponse.builder().message("success").status(false).data(driverList).build();
			} else {
				List<DriverDto> driverDtoList = driverList.stream().map(new Function<Driver, DriverDto>() {
					@Override
					public DriverDto apply(Driver d) {
						Optional<Country> country = countryDao.findById(d.getCountryId());
						Optional<State> state = stateDao.findById(d.getStateId());
						Optional<City> city = cityDao.findById(d.getCityId());

						DriverDto dDto = new DriverDto();

						dDto.setId(d.getId());
						dDto.setCountryId(d.getCountryId());
						dDto.setStateId(d.getStateId());
						dDto.setCityId(d.getCityId());
						dDto.setDriverDescription(d.getDriverDescription());
						dDto.setDriverDeviceId(d.getDriverDeviceId());
						dDto.setDriverDeviceName(d.getDriverDeviceName());
						dDto.setDriverDeviceToken(d.getDriverDeviceToken());
						dDto.setDriverDeviceType(d.getDriverDeviceType());
						dDto.setDriverImage(d.getDriverImage());
						dDto.setDriverLoginStatus(d.getDriverLoginStatus());
						dDto.setDriverOtp(d.getDriverOtp());
						dDto.setDriverReferralBy(d.getDriverReferralCode());
						dDto.setDriverReferralCode(d.getDriverReferralCode());
						dDto.setDriverRideTakingStatus(d.getDriverRideTakingStatus());
						dDto.setPhoneNumber(d.getPhoneNumber());
						dDto.setLastName(d.getLastName());
						dDto.setIsActive(d.getIsActive());
						dDto.setFirstName(d.getFirstName());
						dDto.setEmail(d.getEmail());
						dDto.setDriverStatus(d.getDriverStatus());

						if (!country.isPresent())
							dDto.setCountryName(StringsConstant.EMPTY.value);
						else
							dDto.setCountryName(country.get().getCountryName());

						if (!state.isPresent())
							dDto.setStateName(StringsConstant.EMPTY.value);
						else
							dDto.setStateName(state.get().getStateName());

						if (!city.isPresent())
							dDto.setCityName(StringsConstant.EMPTY.value);
						else
							dDto.setCityName(city.get().getCityName());

						return dDto;
					}
				}).collect(Collectors.toList());

				logger.info("driver list to be send " + driverDtoList);

				return BaseMessageResponse.builder().message("success").status(true).data(driverDtoList).build();

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("exception " + e);
		}
		return BaseMessageResponse.builder().message("Something weny wrong").status(false).build();
	}

	public BaseMessageResponse<Object> deleteDriver(Long driverId) {
		Optional<Driver> driver = driverDao.findById(driverId);

		if (!driver.isPresent()) {
			return BaseMessageResponse.builder().message("driver not present").status(false).data(driver).build();

		} else {
			driverDao.deleteById(driver.get().getId());
			return BaseMessageResponse.builder().message("success").status(true).data(driver).build();
		}
	}

	public BaseMessageResponse<Object> updateDriver(DriverDto driver) {
		Driver oldDriver = driverDao.findById(driver.getId()).get();

		if (driver.getId() != null) {
			if (driver.getCountryId() != null)
				oldDriver.setCountryId(driver.getCountryId());
			else
				oldDriver.setCountryId(oldDriver.getCountryId());

			if (driver.getStateId() != null)
				oldDriver.setStateId(driver.getStateId());
			else
				oldDriver.setStateId(oldDriver.getStateId());

			if (driver.getCityId() != null)
				oldDriver.setCityId(driver.getCityId());
			else
				oldDriver.setCityId(oldDriver.getCityId());

			if (driver.getFirstName() != null)
				oldDriver.setFirstName(driver.getFirstName());
			else
				oldDriver.setFirstName(oldDriver.getFirstName());

			if (driver.getLastName() != null)
				oldDriver.setLastName(driver.getLastName());
			else
				oldDriver.setLastName(oldDriver.getLastName());

			if (driver.getDriverDescription() != null)
				oldDriver.setDriverDescription(driver.getDriverDescription());
			else
				oldDriver.setDriverDescription(oldDriver.getDriverDescription());

			if (driver.getCityId() != null)
				oldDriver.setCityId(driver.getCityId());
			else
				oldDriver.setCityId(oldDriver.getCityId());

			if (driver.getDriverLoginStatus() != null)
				oldDriver.setDriverLoginStatus(driver.getDriverLoginStatus());
			else
				oldDriver.setDriverLoginStatus(oldDriver.getDriverLoginStatus());

			if (driver.getDriverStatus() != null)
				oldDriver.setDriverStatus(driver.getDriverStatus());
			else
				oldDriver.setDriverStatus(oldDriver.getDriverStatus());

			if (driver.getDriverRideTakingStatus() != null)
				oldDriver.setDriverRideTakingStatus(driver.getDriverRideTakingStatus());
			else
				oldDriver.setDriverRideTakingStatus(oldDriver.getDriverRideTakingStatus());

			Driver savedDriver = driverDao.save(oldDriver);
			return BaseMessageResponse.builder().message("success").status(true).data(savedDriver).build();
		} else {
			return BaseMessageResponse.builder().message("driver not present").status(false).data(driver).build();
		}
	}

	public BaseMessageResponse<Object> driverById(Long driverId) {
		Optional<Driver> driver = driverDao.findById(driverId);

		if (!driver.isPresent()) {
			return BaseMessageResponse.builder().message("driver not present").status(false).data(driver).build();

		} else {
			Driver d = driver.get();
			DriverDto dDto = new DriverDto();
			Optional<Country> country = countryDao.findById(d.getCountryId());
			Optional<State> state = stateDao.findById(d.getStateId());
			Optional<City> city = cityDao.findById(d.getCityId());

			dDto.setId(d.getId());
			dDto.setCountryId(d.getCountryId());
			dDto.setStateId(d.getStateId());
			dDto.setCityId(d.getCityId());
			dDto.setDriverDescription(d.getDriverDescription());
			dDto.setDriverDeviceId(d.getDriverDeviceId());
			dDto.setDriverDeviceName(d.getDriverDeviceName());
			dDto.setDriverDeviceToken(d.getDriverDeviceToken());
			dDto.setDriverDeviceType(d.getDriverDeviceType());
			dDto.setDriverImage(d.getDriverImage());
			dDto.setDriverLoginStatus(d.getDriverLoginStatus());
			dDto.setDriverOtp(d.getDriverOtp());
			dDto.setDriverReferralBy(d.getDriverReferralCode());
			dDto.setDriverReferralCode(d.getDriverReferralCode());
			dDto.setDriverRideTakingStatus(d.getDriverRideTakingStatus());
			dDto.setPhoneNumber(d.getPhoneNumber());
			dDto.setLastName(d.getLastName());
			dDto.setIsActive(d.getIsActive());
			dDto.setFirstName(d.getFirstName());
			dDto.setEmail(d.getEmail());
			dDto.setDriverStatus(d.getDriverStatus());

			if (!country.isPresent())
				dDto.setCountryName(StringsConstant.EMPTY.value);
			else
				dDto.setCountryName(country.get().getCountryName());

			if (!state.isPresent())
				dDto.setStateName(StringsConstant.EMPTY.value);
			else
				dDto.setStateName(state.get().getStateName());

			if (!city.isPresent())
				dDto.setCityName(StringsConstant.EMPTY.value);
			else
				dDto.setCityName(city.get().getCityName());

			return BaseMessageResponse.builder().message("success").status(true).data(dDto).build();
		}
	}

//	public BaseMessageResponse<Object> getAllDriverByStateId(Optional<Long> stateId) {
//		try {
//
//			List<Driver> driverList = driverDao.findByStateId(stateId);
//
//			if (driverList.isEmpty()) {
//				return BaseMessageResponse.builder().message("success").status(false).data(driverList).build();
//			} else {
//				List<DriverDto> driverDtoList = driverList.stream().map(new Function<Driver, DriverDto>() {
//					@Override
//					public DriverDto apply(Driver s) {
//						Optional<Country> country = countryDao.findById(s.getCountryId());
//						Optional<State> state = stateDao.findById(s.getStateId());
//
//						DriverDto sDto = new DriverDto();
//
//						sDto.setId(s.getId());
//						sDto.setCountryId(s.getCountryId());
//						sDto.setStateId(s.getStateId());
//						sDto.setDriverName(s.getDriverName());
//
//						if (!country.isPresent())
//							sDto.setCountryName(StringsConstant.EMPTY.value);
//						else
//							sDto.setCountryName(country.get().getCountryName());
//
//						if (!state.isPresent())
//							sDto.setStateName(StringsConstant.EMPTY.value);
//						else
//							sDto.setStateName(state.get().getStateName());
//
//						if (s.getIsActive().equals(true))
//							sDto.setIsActive(NumbersConstant.ONE.value);
//						else
//							sDto.setIsActive(NumbersConstant.ZERO.value);
//
//						return sDto;
//					}
//				}).collect(Collectors.toList());
//
//				logger.info("driver list to be send " + driverDtoList);
//
//				return BaseMessageResponse.builder().message("success").status(true).data(driverDtoList).build();
//
//			}
//		} catch (Exception e) {
//			logger.error("Exception " + e);
//			e.printStackTrace();
//		}
//		return BaseMessageResponse.builder().message("Something went wrong").status(false).build();
//
//	}

}
