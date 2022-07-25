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
import com.booking.yoya.dao.CountryDao;
import com.booking.yoya.dto.CountryDto;
import com.booking.yoya.model.Country;
import com.booking.yoya.response.BaseMessageResponse;

@Service
public class CountryService {

	private static Logger logger = LogManager.getLogger();

	@Autowired
	private CountryDao countryDao;

	public BaseMessageResponse<Object> createNewCountry(Country country) {
		Optional<Country> countryName = countryDao.findByCountryName(country.getCountryName());

		if (countryName.isPresent()) {
			return BaseMessageResponse.builder().message("country already present").status(true).data(countryName)
					.build();

		} else {
			Country savedCountry = countryDao.save(country);
			return BaseMessageResponse.builder().message("success").status(true).data(savedCountry).build();
		}
	}

	public BaseMessageResponse<Object> getAllCoutries() {
		try {
			List<Country> countryList = countryDao.findAll();
			if (countryList.isEmpty()) {
				return BaseMessageResponse.builder().message("success").status(false).data(countryList).build();
			} else {
				List<CountryDto> countryDtoList = countryList.stream().map(new Function<Country, CountryDto>() {
					@Override
					public CountryDto apply(Country c) {
						CountryDto cDto = new CountryDto();

						cDto.setId(c.getId());
						cDto.setCountryCode(c.getCountryCode());
						cDto.setCountryDialingCode(c.getCountryDialingCode());
						cDto.setCountryName(c.getCountryName());
						cDto.setFlag(c.getFlag());
						if (c.getIsActive().equals(true))
							cDto.setIsActive(NumbersConstant.ONE.value);
						else
							cDto.setIsActive(NumbersConstant.ZERO.value);
						return cDto;
					}
				}).collect(Collectors.toList());

				return BaseMessageResponse.builder().message("success").status(true).data(countryDtoList).build();

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("exception " + e);
		}
		return BaseMessageResponse.builder().message("Something weny wrong").status(false).build();

	}

	public BaseMessageResponse<Object> deleteCountry(Long countryId) {
		Optional<Country> country = countryDao.findById(countryId);

		if (!country.isPresent()) {
			return BaseMessageResponse.builder().message("country not present").status(false).data(country).build();

		} else {
			countryDao.deleteById(country.get().getId());
			return BaseMessageResponse.builder().message("success").status(true).data(country).build();
		}
	}

	public BaseMessageResponse<Object> updateCountry(CountryDto country) {
		Country oldCountry = countryDao.findById(country.getId()).get();

		if (oldCountry.getId() != null) {
			oldCountry.setCountryCode(country.getCountryCode());
			oldCountry.setCountryDialingCode(country.getCountryDialingCode());
			oldCountry.setCountryName(country.getCountryName());
			oldCountry.setFlag(country.getFlag());

			if (country.getIsActive() == NumbersConstant.ONE.value)
				oldCountry.setIsActive(true);
			else
				oldCountry.setIsActive(false);

			Country savedCountry = countryDao.save(oldCountry);
			return BaseMessageResponse.builder().message("success").status(true).data(savedCountry).build();
		} else {
			return BaseMessageResponse.builder().message("country not present").status(false).data(country).build();
		}
	}

	public BaseMessageResponse<Object> countryById(Long countryId) {
		try {
			Optional<Country> country = countryDao.findById(countryId);

			if (!country.isPresent()) {
				return BaseMessageResponse.builder().message("country not present").status(false).data(country).build();

			} else {
				CountryDto cDto = new CountryDto();
				cDto.setId(country.get().getId());
				cDto.setCountryCode(country.get().getCountryCode());
				cDto.setCountryDialingCode(country.get().getCountryDialingCode());
				cDto.setCountryName(country.get().getCountryName());
				cDto.setFlag(country.get().getFlag());

				if (country.get().getIsActive().equals(true))
					cDto.setIsActive(NumbersConstant.ONE.value);
				else
					cDto.setIsActive(NumbersConstant.ZERO.value);

				return BaseMessageResponse.builder().message("success").status(true).data(cDto).build();
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("exception " + e);
		}
		return BaseMessageResponse.builder().message("Something weny wrong").status(false).build();

	}

}
