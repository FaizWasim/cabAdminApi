package com.booking.yoya.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.yoya.dto.DriverDto;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.DriverService;

@RestController
@RequestMapping("api/v1/driver")
public class DriverController {
	private static Logger logger = LogManager.getLogger();
	@Autowired
	private DriverService driverService;

//	@GetMapping(value = "list")
////	@PreAuthorize("hasRole('Admin')")
//	public ServiceResponse<?> countryList(@RequestParam(name = "stateId", required = false) Optional<Long> stateId) {
//		logger.info("driver list in controller");
//		try {
//			if (!stateId.isPresent())
//
//				return new ServiceResponse<BaseMessageResponse>(driverService.getAllDrivers());
//			else {
//
//				if (stateId.get() != null) {
//
//					return new ServiceResponse<BaseMessageResponse>(driverService.getAllDriverByStateId(stateId));
//				}
//			}
//		} catch (Exception e) {
//			logger.error("exeption= " + e);
//			e.printStackTrace();
//		}
//
//		return new ServiceResponse<BaseMessageResponse>(driverService.getAllDrivers());
//	}

	@GetMapping(value = "list")
//	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> driverList() {
		try {

			return new ServiceResponse<BaseMessageResponse>(driverService.getAllDrivers());

		} catch (Exception e) {
			logger.error("exeption= " + e);
			e.printStackTrace();
			return new ServiceResponse<BaseMessageResponse>(
					BaseMessageResponse.builder().message("success").status(false).data(e.getMessage()).build());
		}

	}

	@DeleteMapping(value = "{driverId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> deleteDriver(@PathVariable Long driverId) {
		return new ServiceResponse<BaseMessageResponse>(driverService.deleteDriver(driverId));
	}

	@GetMapping(value = "{driverId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> driverById(@PathVariable Long driverId) {
		return new ServiceResponse<BaseMessageResponse>(driverService.driverById(driverId));
	}

	@PutMapping(value = "{driverId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> updateDriver(@PathVariable Long driverId, @RequestBody DriverDto driver) {
		driver.setId(driverId);
		return new ServiceResponse<BaseMessageResponse>(driverService.updateDriver(driver));
	}
}
