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

import com.booking.yoya.dto.CurrencyDto;
import com.booking.yoya.model.Currency;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.CurrencyService;

@RestController
@RequestMapping("api/v1/currency")
public class CurrencyController {
	@Autowired
	private CurrencyService currencyService;

	@PostMapping(value = "create")
	public ServiceResponse<?> createCurrency(@RequestBody Currency currency) {
		return new ServiceResponse<BaseMessageResponse>(currencyService.createNewCurrency(currency));
	}

	@GetMapping(value = "list")
//	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> countryList() {
		return new ServiceResponse<BaseMessageResponse>(currencyService.getAllCurrencys());
	}

	@DeleteMapping(value = "{currencyId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> deleteCurrency(@PathVariable Long currencyId) {
		return new ServiceResponse<BaseMessageResponse>(currencyService.deleteCurrency(currencyId));
	}

	@GetMapping(value = "{currencyId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> currencyById(@PathVariable Long currencyId) {
		return new ServiceResponse<BaseMessageResponse>(currencyService.currencyById(currencyId));
	}

	@PutMapping(value = "{currencyId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> updateCurrency(@PathVariable Long currencyId, @RequestBody CurrencyDto currency) {
		currency.setId(currencyId);
		return new ServiceResponse<BaseMessageResponse>(currencyService.updateCurrency(currency));
	}
}
