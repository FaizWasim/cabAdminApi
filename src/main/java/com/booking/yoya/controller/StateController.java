package com.booking.yoya.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.yoya.dto.StateDto;
import com.booking.yoya.model.State;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.StateService;

@RestController
@RequestMapping("api/v1/state")
public class StateController {

	private static final Logger logger = LogManager.getLogger(CountryController.class);

	@Autowired
	private StateService stateService;

	@PostMapping(value = "create")
	public ServiceResponse<?> createState(@RequestBody State state) {
		return new ServiceResponse<BaseMessageResponse>(stateService.createNewState(state));
	}

	@GetMapping(value = "list")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> stateList(
			@RequestParam(name = "countryId", required = false) Optional<Long> countryId) {
		try {
			if (!countryId.isPresent())

				return new ServiceResponse<BaseMessageResponse>(stateService.getAllStates());

			else {

				if (countryId.get() != null) {
					return new ServiceResponse<BaseMessageResponse>(
							stateService.findAllStatesByCountryId(countryId.get()), HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			logger.error("exeption= " + e);
			e.printStackTrace();
		}

		return new ServiceResponse<BaseMessageResponse>(stateService.getAllStates());
	}

	@DeleteMapping(value = "{stateId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> deleteState(@PathVariable Long stateId) {
		return new ServiceResponse<BaseMessageResponse>(stateService.deleteState(stateId));
	}

	@GetMapping(value = "{stateId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> stateById(@PathVariable Long stateId) {
		return new ServiceResponse<BaseMessageResponse>(stateService.stateById(stateId));
	}

	@PutMapping(value = "{stateId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> updateState(@PathVariable Long stateId, @RequestBody StateDto state) {
		state.setId(stateId);
		return new ServiceResponse<BaseMessageResponse>(stateService.updateState(state));
	}
}
