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

import com.booking.yoya.dto.VehicleNormalPriceDto;
import com.booking.yoya.model.VehicleNormalPrice;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.VehicleNormalPriceService;

@RestController
@RequestMapping("api/v1/vehicleNormalPrice")
public class VehicleNormalPriceController {
	@Autowired
	private VehicleNormalPriceService vehicleNormalPriceService;

	@PostMapping(value = "create")
	public ServiceResponse<?> createVehicleNormalPrice(@RequestBody VehicleNormalPrice vehicleNormalPrice) {
		return new ServiceResponse<BaseMessageResponse>(vehicleNormalPriceService.createNewVehicleNormalPrice(vehicleNormalPrice));
	}

	@GetMapping(value = "list")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> vehicleNormalPriceList() {
		return new ServiceResponse<BaseMessageResponse>(vehicleNormalPriceService.getAllVehicleNormalPrices());
	}

	@DeleteMapping(value = "{vehicleNormalPriceId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> deleteVehicleNormalPrice(@PathVariable Long vehicleNormalPriceId) {
		return new ServiceResponse<BaseMessageResponse>(vehicleNormalPriceService.deleteVehicleNormalPrice(vehicleNormalPriceId));
	}

	@GetMapping(value = "{vehicleNormalPriceId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> vehicleNormalPriceById(@PathVariable Long vehicleNormalPriceId) {
		return new ServiceResponse<BaseMessageResponse>(vehicleNormalPriceService.vehicleNormalPriceById(vehicleNormalPriceId));
	}

	@PutMapping(value = "{vehicleNormalPriceId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> updateVehicleNormalPrice(@PathVariable Long vehicleNormalPriceId, @RequestBody VehicleNormalPriceDto vehicleNormalPrice) {
		vehicleNormalPrice.setId(vehicleNormalPriceId);
		return new ServiceResponse<BaseMessageResponse>(vehicleNormalPriceService.updateVehicleNormalPrice(vehicleNormalPrice));
	}
}
