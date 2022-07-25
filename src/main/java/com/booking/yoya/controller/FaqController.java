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

import com.booking.yoya.dto.FaqDto;
import com.booking.yoya.model.Faq;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.FaqService;

@RestController
@RequestMapping("api/v1/faq")
public class FaqController {
	@Autowired
	private FaqService faqService;

	@PostMapping(value = "create")
	public ServiceResponse<?> createFaq(@RequestBody Faq faq) {
		return new ServiceResponse<BaseMessageResponse>(faqService.createNewFaq(faq));
	}

	@GetMapping(value = "list")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> countryList() {
		return new ServiceResponse<BaseMessageResponse>(faqService.getAllFaq());
	}

	@DeleteMapping(value = "{faqId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> deleteFaq(@PathVariable Long faqId) {
		return new ServiceResponse<BaseMessageResponse>(faqService.deleteFaq(faqId));
	}

	@GetMapping(value = "{faqId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> faqById(@PathVariable Long faqId) {
		return new ServiceResponse<BaseMessageResponse>(faqService.faqById(faqId));
	}

	@PutMapping(value = "{faqId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> updateFaq(@PathVariable Long faqId,
			@RequestBody FaqDto faq) {
		faq.setId(faqId);
		return new ServiceResponse<BaseMessageResponse>(faqService.updateFaq(faq));
	}
}
