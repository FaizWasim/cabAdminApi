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

import com.booking.yoya.dto.RentalPackagePriceDto;
import com.booking.yoya.model.RentalPackagePrice;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.RentalPackagePriceService;

@RestController
@RequestMapping("api/v1/rentalPackagePrice")
public class RentalPackagePriceController {
	@Autowired
	private RentalPackagePriceService rentalPackagePriceService;

	@PostMapping(value = "create")
	public ServiceResponse<?> createRentalPackagePrice(@RequestBody RentalPackagePrice rentalPackagePrice) {
		return new ServiceResponse<BaseMessageResponse>(rentalPackagePriceService.createNewRentalPackagePrice(rentalPackagePrice));
	}

	@GetMapping(value = "list")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> rentalPackagePriceList() {
		return new ServiceResponse<BaseMessageResponse>(rentalPackagePriceService.getAllRentalPackagePrices());
	}

	@DeleteMapping(value = "{rentalPackagePriceId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> deleteRentalPackagePrice(@PathVariable Long rentalPackagePriceId) {
		return new ServiceResponse<BaseMessageResponse>(rentalPackagePriceService.deleteRentalPackagePrice(rentalPackagePriceId));
	}

	@GetMapping(value = "{rentalPackagePriceId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> rentalPackagePriceById(@PathVariable Long rentalPackagePriceId) {
		return new ServiceResponse<BaseMessageResponse>(rentalPackagePriceService.rentalPackagePriceById(rentalPackagePriceId));
	}

	@PutMapping(value = "{rentalPackagePriceId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> updateRentalPackagePrice(@PathVariable Long rentalPackagePriceId, @RequestBody RentalPackagePriceDto rentalPackagePrice) {
		rentalPackagePrice.setId(rentalPackagePriceId);
		return new ServiceResponse<BaseMessageResponse>(rentalPackagePriceService.updateRentalPackagePrice(rentalPackagePrice));
	}
}
