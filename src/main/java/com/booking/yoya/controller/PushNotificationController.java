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

import com.booking.yoya.dto.PushNotificationDto;
import com.booking.yoya.model.PushNotification;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.PushNotificationService;

@RestController
@RequestMapping("api/v1/pushNotification")
public class PushNotificationController {
	@Autowired
	private PushNotificationService pushNotificationService;

	@PostMapping(value = "create")
	public ServiceResponse<?> createPushNotification(@RequestBody PushNotification pushNotification) {
		return new ServiceResponse<BaseMessageResponse>(pushNotificationService.createNewPushNotification(pushNotification));
	}

	@GetMapping(value = "list")
//	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> countryList() {
		return new ServiceResponse<BaseMessageResponse>(pushNotificationService.getAllPushNotifications());
	}

	@DeleteMapping(value = "{pushNotificationId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> deletePushNotification(@PathVariable Long pushNotificationId) {
		return new ServiceResponse<BaseMessageResponse>(pushNotificationService.deletePushNotification(pushNotificationId));
	}

	@GetMapping(value = "{pushNotificationId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> pushNotificationById(@PathVariable Long pushNotificationId) {
		return new ServiceResponse<BaseMessageResponse>(pushNotificationService.pushNotificationById(pushNotificationId));
	}

	@PutMapping(value = "{pushNotificationId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> updatePushNotification(@PathVariable Long pushNotificationId, @RequestBody PushNotificationDto pushNotification) {
		pushNotification.setId(pushNotificationId);
		return new ServiceResponse<BaseMessageResponse>(pushNotificationService.updatePushNotification(pushNotification));
	}
}
