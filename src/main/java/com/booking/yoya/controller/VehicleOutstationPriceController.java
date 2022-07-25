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

import com.booking.yoya.dto.VehicleOutstationPriceDto;
import com.booking.yoya.model.VehicleOutstationPrice;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.VehicleOutStationPriceService;

@RestController
@RequestMapping("api/v1/vehicleOutstationPrice")
public class VehicleOutstationPriceController {
	@Autowired
	private VehicleOutStationPriceService vehicleOutstationPriceService;

	@PostMapping(value = "create")
	public ServiceResponse<?> createVehicleOutstationPrice(@RequestBody VehicleOutstationPrice vehicleOutstationPrice) {
		return new ServiceResponse<BaseMessageResponse>(vehicleOutstationPriceService.createNewVehicleOutstationPrice(vehicleOutstationPrice));
	}

	@GetMapping(value = "list")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> countryList() {
		return new ServiceResponse<BaseMessageResponse>(vehicleOutstationPriceService.getAllVehicleOutstationPrices());
	}

	@DeleteMapping(value = "{vehicleOutstationPriceId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> deleteVehicleOutstationPrice(@PathVariable Long vehicleOutstationPriceId) {
		return new ServiceResponse<BaseMessageResponse>(vehicleOutstationPriceService.deleteVehicleOutstationPrice(vehicleOutstationPriceId));
	}

	@GetMapping(value = "{vehicleOutstationPriceId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> vehicleOutstationPriceById(@PathVariable Long vehicleOutstationPriceId) {
		return new ServiceResponse<BaseMessageResponse>(vehicleOutstationPriceService.vehicleOutstationPriceById(vehicleOutstationPriceId));
	}

	@PutMapping(value = "{vehicleOutstationPriceId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> updateVehicleOutstationPrice(@PathVariable Long vehicleOutstationPriceId, @RequestBody VehicleOutstationPriceDto vehicleOutstationPrice) {
		vehicleOutstationPrice.setId(vehicleOutstationPriceId);
		return new ServiceResponse<BaseMessageResponse>(
				vehicleOutstationPriceService.updateVehicleOutstationPrice(vehicleOutstationPrice));
	}
}
