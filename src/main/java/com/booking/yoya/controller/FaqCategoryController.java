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

import com.booking.yoya.dto.FaqCategoryDto;
import com.booking.yoya.model.FaqCategory;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.FaqCategoryService;

@RestController
@RequestMapping("api/v1/faqCategory")
public class FaqCategoryController {
	@Autowired
	private FaqCategoryService faqCategoryService;

	@PostMapping(value = "create")
	public ServiceResponse<?> createFaqCategory(@RequestBody FaqCategory faqCategory) {
		return new ServiceResponse<BaseMessageResponse>(faqCategoryService.createNewFaqCategory(faqCategory));
	}

	@GetMapping(value = "list")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> countryList() {
		return new ServiceResponse<BaseMessageResponse>(faqCategoryService.getAllFaqCategory());
	}

	@DeleteMapping(value = "{faqCategoryId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> deleteFaqCategory(@PathVariable Long faqCategoryId) {
		return new ServiceResponse<BaseMessageResponse>(faqCategoryService.deleteFaqCategory(faqCategoryId));
	}

	@GetMapping(value = "{faqCategoryId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> faqCategoryById(@PathVariable Long faqCategoryId) {
		return new ServiceResponse<BaseMessageResponse>(faqCategoryService.faqCategoryById(faqCategoryId));
	}

	@PutMapping(value = "{faqCategoryId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> updateFaqCategory(@PathVariable Long faqCategoryId, @RequestBody FaqCategoryDto faqCategory) {
		faqCategory.setFaqFor(faqCategoryId);
		return new ServiceResponse<BaseMessageResponse>(faqCategoryService.updateFaqCategory(faqCategory));
	}
}
