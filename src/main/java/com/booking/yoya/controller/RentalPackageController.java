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

import com.booking.yoya.dto.RentalPackageDto;
import com.booking.yoya.model.RentalPackage;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.RentalPackageService;

@RestController
@RequestMapping("api/v1/rentalPackage")
public class RentalPackageController {
	@Autowired
	private RentalPackageService rentalPackageService;

	@PostMapping(value = "create")
	public ServiceResponse<?> createRentalPackage(@RequestBody RentalPackage rentalPackage) {
		return new ServiceResponse<BaseMessageResponse>(rentalPackageService.createNewRentalPackage(rentalPackage));
	}

	@GetMapping(value = "list")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> rentalPackageList() {
		return new ServiceResponse<BaseMessageResponse>(rentalPackageService.getAllRentalPackages());
	}

	@DeleteMapping(value = "{rentalPackageId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> deleteRentalPackage(@PathVariable Long rentalPackageId) {
		return new ServiceResponse<BaseMessageResponse>(rentalPackageService.deleteRentalPackage(rentalPackageId));
	}

	@GetMapping(value = "{rentalPackageId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> rentalPackageById(@PathVariable Long rentalPackageId) {
		return new ServiceResponse<BaseMessageResponse>(rentalPackageService.rentalPackageById(rentalPackageId));
	}

	@PutMapping(value = "{rentalPackageId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> updateRentalPackage(@PathVariable Long rentalPackageId, @RequestBody RentalPackageDto rentalPackage) {
		rentalPackage.setId(rentalPackageId);
		return new ServiceResponse<BaseMessageResponse>(rentalPackageService.updateRentalPackage(rentalPackage));
	}
}
