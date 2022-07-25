package com.booking.yoya.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.yoya.dto.CityDto;
import com.booking.yoya.model.City;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.CityService;

@RestController
@RequestMapping("api/v1/city")
public class CityController {
	private static Logger logger = LogManager.getLogger();
	@Autowired
	private CityService cityService;

	@PostMapping(value = "create")
	public ServiceResponse<?> createCity(@RequestBody City city) {
		return new ServiceResponse<BaseMessageResponse>(cityService.createNewCity(city));
	}

	@GetMapping(value = "list")
//	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> countryList(@RequestParam(name = "stateId", required = false) Optional<Long> stateId) {
		logger.info("city list in controller");
		try {
			if (!stateId.isPresent())

				return new ServiceResponse<BaseMessageResponse>(cityService.getAllCitys());
			else {

				if (stateId.get() != null) {

					return new ServiceResponse<BaseMessageResponse>(cityService.getAllCityByStateId(stateId));
				}
			}
		} catch (Exception e) {
			logger.error("exeption= " + e);
			e.printStackTrace();
		}

		return new ServiceResponse<BaseMessageResponse>(cityService.getAllCitys());
	}

	@DeleteMapping(value = "{cityId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> deleteCity(@PathVariable Long cityId) {
		return new ServiceResponse<BaseMessageResponse>(cityService.deleteCity(cityId));
	}

	@GetMapping(value = "{cityId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> cityById(@PathVariable Long cityId) {
		return new ServiceResponse<BaseMessageResponse>(cityService.cityById(cityId));
	}

	@PutMapping(value = "{cityId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> updateCity(@PathVariable Long cityId, @RequestBody CityDto city) {
		city.setId(cityId);
		return new ServiceResponse<BaseMessageResponse>(cityService.updateCity(city));
	}
}
