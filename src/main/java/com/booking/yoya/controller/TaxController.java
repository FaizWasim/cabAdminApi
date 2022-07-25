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

import com.booking.yoya.dto.TaxDto;
import com.booking.yoya.model.Tax;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.TaxService;

@RestController
@RequestMapping("api/v1/tax")
public class TaxController {
	@Autowired
	private TaxService taxService;

	@PostMapping(value = "create")
	public ServiceResponse<?> createTax(@RequestBody Tax tax) {
		return new ServiceResponse<BaseMessageResponse>(taxService.createNewTax(tax));
	}

	@GetMapping(value = "list")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> countryList() {
		return new ServiceResponse<BaseMessageResponse>(taxService.getAllTaxs());
	}

	@DeleteMapping(value = "{taxId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> deleteTax(@PathVariable Long taxId) {
		return new ServiceResponse<BaseMessageResponse>(taxService.deleteTax(taxId));
	}

	@GetMapping(value = "{taxId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> taxById(@PathVariable Long taxId) {
		return new ServiceResponse<BaseMessageResponse>(taxService.taxById(taxId));
	}

	@PutMapping(value = "{taxId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> updateTax(@PathVariable Long taxId, @RequestBody TaxDto tax) {
		tax.setId(taxId);
		return new ServiceResponse<BaseMessageResponse>(taxService.updateTax(tax));
	}
}
