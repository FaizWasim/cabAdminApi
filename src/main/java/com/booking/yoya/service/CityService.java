package com.booking.yoya.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.yoya.constant.NumbersConstant;
import com.booking.yoya.constant.StringsConstant;
import com.booking.yoya.dao.CityDao;
import com.booking.yoya.dao.CountryDao;
import com.booking.yoya.dao.StateDao;
import com.booking.yoya.dto.CityDto;
import com.booking.yoya.model.City;
import com.booking.yoya.model.Country;
import com.booking.yoya.model.State;
import com.booking.yoya.response.BaseMessageResponse;

@Service
public class CityService {

	private static Logger logger = LogManager.getLogger();

	@Autowired
	private CityDao cityDao;

	@Autowired
	private CountryDao countryDao;

	@Autowired
	private StateDao stateDao;

	public BaseMessageResponse<Object> createNewCity(City city) {
		Optional<City> cityName = cityDao.findByCityName(city.getCityName());

		if (cityName.isPresent()) {
			return BaseMessageResponse.builder().message("city already present").status(true).data(cityName).build();

		} else {
			City savedCity = cityDao.save(city);
			return BaseMessageResponse.builder().message("success").status(true).data(savedCity).build();
		}
	}

	public BaseMessageResponse<Object> getAllCitys() {
		try {

			logger.info("city list " + cityDao.findAll());

			List<City> cityList = cityDao.findAll();
			if (cityList.isEmpty()) {
				return BaseMessageResponse.builder().message("success").status(false).data(cityList).build();
			} else {
				List<CityDto> cityDtoList = cityList.stream().map(new Function<City, CityDto>() {
					@Override
					public CityDto apply(City s) {
						Optional<Country> country = countryDao.findById(s.getCountryId());
						Optional<State> state = stateDao.findById(s.getStateId());

						CityDto sDto = new CityDto();

						sDto.setId(s.getId());
						sDto.setCountryId(s.getCountryId());
						sDto.setStateId(s.getStateId());
						sDto.setCityName(s.getCityName());

						if (!country.isPresent())
							sDto.setCountryName(StringsConstant.EMPTY.value);
						else
							sDto.setCountryName(country.get().getCountryName());

						if (!state.isPresent())
							sDto.setStateName(StringsConstant.EMPTY.value);
						else
							sDto.setStateName(state.get().getStateName());

						if (s.getIsActive().equals(true))
							sDto.setIsActive(NumbersConstant.ONE.value);
						else
							sDto.setIsActive(NumbersConstant.ZERO.value);

						return sDto;
					}
				}).collect(Collectors.toList());

				logger.info("city list to be send " + cityDtoList);

				return BaseMessageResponse.builder().message("success").status(true).data(cityDtoList).build();

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("exception " + e);
		}
		return BaseMessageResponse.builder().message("Something weny wrong").status(false).build();
	}

	public BaseMessageResponse<Object> deleteCity(Long cityId) {
		Optional<City> city = cityDao.findById(cityId);

		if (!city.isPresent()) {
			return BaseMessageResponse.builder().message("city not present").status(false).data(city).build();

		} else {
			cityDao.deleteById(city.get().getId());
			return BaseMessageResponse.builder().message("success").status(true).data(city).build();
		}
	}

	public BaseMessageResponse<Object> updateCity(CityDto city) {
		City oldCity = cityDao.findById(city.getId()).get();

		if (city.getId() != null) {
			oldCity.setCountryId(city.getCountryId());
			oldCity.setStateId(city.getStateId());
			oldCity.setCityName(city.getCityName());

			if (city.getIsActive() == NumbersConstant.ONE.value)
				oldCity.setIsActive(true);
			else
				oldCity.setIsActive(false);

			City savedCity = cityDao.save(oldCity);
			return BaseMessageResponse.builder().message("success").status(true).data(savedCity).build();
		} else {
			return BaseMessageResponse.builder().message("city not present").status(false).data(city).build();
		}
	}

	public BaseMessageResponse<Object> cityById(Long cityId) {
		Optional<City> city = cityDao.findById(cityId);

		if (!city.isPresent()) {
			return BaseMessageResponse.builder().message("city not present").status(false).data(city).build();

		} else {
			Optional<Country> country = countryDao.findById(city.get().getCountryId());
			Optional<State> state = stateDao.findById(city.get().getStateId());
			CityDto sDto = new CityDto();
			sDto.setId(city.get().getId());
			sDto.setCountryId(city.get().getCountryId());
			sDto.setStateId(city.get().getStateId());
			sDto.setCityName(city.get().getCityName());

			if (!country.isPresent())
				sDto.setCountryName(StringsConstant.EMPTY.value);
			else
				sDto.setCountryName(country.get().getCountryName());

			if (!state.isPresent())
				sDto.setStateName(StringsConstant.EMPTY.value);
			else
				sDto.setStateName(state.get().getStateName());

			if (city.get().getIsActive().equals(true))
				sDto.setIsActive(NumbersConstant.ONE.value);
			else
				sDto.setIsActive(NumbersConstant.ZERO.value);

			return BaseMessageResponse.builder().message("success").status(true).data(sDto).build();
		}
	}

	public BaseMessageResponse<Object> getAllCityByStateId(Optional<Long> stateId) {
		try {

			List<City> cityList = cityDao.findByStateId(stateId);

			if (cityList.isEmpty()) {
				return BaseMessageResponse.builder().message("success").status(false).data(cityList).build();
			} else {
				List<CityDto> cityDtoList = cityList.stream().map(new Function<City, CityDto>() {
					@Override
					public CityDto apply(City s) {
						Optional<Country> country = countryDao.findById(s.getCountryId());
						Optional<State> state = stateDao.findById(s.getStateId());

						CityDto sDto = new CityDto();

						sDto.setId(s.getId());
						sDto.setCountryId(s.getCountryId());
						sDto.setStateId(s.getStateId());
						sDto.setCityName(s.getCityName());

						if (!country.isPresent())
							sDto.setCountryName(StringsConstant.EMPTY.value);
						else
							sDto.setCountryName(country.get().getCountryName());

						if (!state.isPresent())
							sDto.setStateName(StringsConstant.EMPTY.value);
						else
							sDto.setStateName(state.get().getStateName());

						if (s.getIsActive().equals(true))
							sDto.setIsActive(NumbersConstant.ONE.value);
						else
							sDto.setIsActive(NumbersConstant.ZERO.value);

						return sDto;
					}
				}).collect(Collectors.toList());

				logger.info("city list to be send " + cityDtoList);

				return BaseMessageResponse.builder().message("success").status(true).data(cityDtoList).build();

			}
		} catch (Exception e) {
			logger.error("Exception " + e);
			e.printStackTrace();
		}
		return BaseMessageResponse.builder().message("Something went wrong").status(false).build();

	}

}
