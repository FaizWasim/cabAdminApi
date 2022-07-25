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

import com.booking.yoya.dto.ProcessDto;
import com.booking.yoya.model.Process;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.ProcessService;

@RestController
@RequestMapping("api/v1/process")
public class ProcessController {
	@Autowired
	private ProcessService processService;

	@PostMapping(value = "create")
	public ServiceResponse<?> createProcess(@RequestBody Process process) {
		return new ServiceResponse<BaseMessageResponse>(processService.createNewProcess(process));
	}

	@GetMapping(value = "list")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> countryList() {
		return new ServiceResponse<BaseMessageResponse>(processService.getAllProcesss());
	}

	@DeleteMapping(value = "{processId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> deleteProcess(@PathVariable Long processId) {
		return new ServiceResponse<BaseMessageResponse>(processService.deleteProcess(processId));
	}

	@GetMapping(value = "{processId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> processById(@PathVariable Long processId) {
		return new ServiceResponse<BaseMessageResponse>(processService.processById(processId));
	}

	@PutMapping(value = "{processId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> updateProcess(@PathVariable Long processId, @RequestBody ProcessDto process) {
		process.setId(processId);
		return new ServiceResponse<BaseMessageResponse>(processService.updateProcess(process));
	}
}
