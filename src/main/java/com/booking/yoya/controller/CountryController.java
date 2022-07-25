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

import com.booking.yoya.dto.CountryDto;
import com.booking.yoya.model.Country;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.CountryService;

@RestController
@RequestMapping("api/v1/country")
public class CountryController {
	@Autowired
	private CountryService countryService;

	@PostMapping(value = "create")
	public ServiceResponse<?> createCountry(@RequestBody Country country) {
		return new ServiceResponse<BaseMessageResponse>(countryService.createNewCountry(country));
	}

	@GetMapping(value = "list")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> countryList() {
		return new ServiceResponse<BaseMessageResponse>(countryService.getAllCoutries());
	}

	@DeleteMapping(value = "{countryId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> deleteCountry(@PathVariable Long countryId) {
		return new ServiceResponse<BaseMessageResponse>(countryService.deleteCountry(countryId));
	}

	@GetMapping(value = "{countryId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> countryById(@PathVariable Long countryId) {
		return new ServiceResponse<BaseMessageResponse>(countryService.countryById(countryId));
	}

	@PutMapping(value = "{countryId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> updateCountry(@PathVariable Long countryId, @RequestBody CountryDto country) {
		country.setId(countryId);
		return new ServiceResponse<BaseMessageResponse>(countryService.updateCountry(country));
	}
}
