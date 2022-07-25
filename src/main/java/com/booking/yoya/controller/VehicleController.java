package com.booking.yoya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.yoya.dto.VehicleDto;
import com.booking.yoya.model.Vehicle;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.VehicleService;

@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {
	@Autowired
	private VehicleService vehicleService;

	@PostMapping(value = "create")
	public ServiceResponse<?> createVehicle(@ModelAttribute VehicleDto vehicle) {
		return new ServiceResponse<BaseMessageResponse>(vehicleService.createNewVehicle(vehicle));
	}

	@GetMapping(value = "list")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> countryList() {
		return new ServiceResponse<BaseMessageResponse>(vehicleService.getAllVehicles());
	}

	@DeleteMapping(value = "{vehicleId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> deleteVehicle(@PathVariable Long vehicleId) {
		return new ServiceResponse<BaseMessageResponse>(vehicleService.deleteVehicle(vehicleId));
	}

	@GetMapping(value = "{vehicleId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> vehicleById(@PathVariable Long vehicleId) {
		return new ServiceResponse<BaseMessageResponse>(vehicleService.vehicleById(vehicleId));
	}

	@PutMapping(value = "{vehicleId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> updateVehicle(@PathVariable Long vehicleId, @ModelAttribute VehicleDto vehicle) {
		vehicle.setId(vehicleId);
		return new ServiceResponse<BaseMessageResponse>(vehicleService.updateVehicle(vehicle));
	}
}
