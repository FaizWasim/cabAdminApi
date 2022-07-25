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

import com.booking.yoya.dto.RideCancellationDto;
import com.booking.yoya.model.RideCancellation;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.RideCancellationService;

@RestController
@RequestMapping("api/v1/rideCancellation")
public class RideCancellationController {
	@Autowired
	private RideCancellationService rideCancellationService;

	@PostMapping(value = "create")
	public ServiceResponse<?> createRideCancellation(@RequestBody RideCancellation rideCancellation) {
		return new ServiceResponse<BaseMessageResponse>(rideCancellationService.createNewRideCancellation(rideCancellation));
	}

	@GetMapping(value = "list")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> countryList() {
		return new ServiceResponse<BaseMessageResponse>(rideCancellationService.getAllRideCancellations());
	}

	@DeleteMapping(value = "{rideCancellationId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> deleteRideCancellation(@PathVariable Long rideCancellationId) {
		return new ServiceResponse<BaseMessageResponse>(rideCancellationService.deleteRideCancellation(rideCancellationId));
	}

	@GetMapping(value = "{rideCancellationId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> rideCancellationById(@PathVariable Long rideCancellationId) {
		return new ServiceResponse<BaseMessageResponse>(rideCancellationService.rideCancellationById(rideCancellationId));
	}

	@PutMapping(value = "{rideCancellationId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> updateRideCancellation(@PathVariable Long rideCancellationId, @RequestBody RideCancellationDto rideCancellation) {
		rideCancellation.setId(rideCancellationId);
		return new ServiceResponse<BaseMessageResponse>(rideCancellationService.updateRideCancellation(rideCancellation));
	}
}
