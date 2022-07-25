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

import com.booking.yoya.dto.LanguageDto;
import com.booking.yoya.model.Language;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.LanguageService;

@RestController
@RequestMapping("api/v1/language")
public class LanguageController {
	@Autowired
	private LanguageService languageService;

	@PostMapping(value = "create")
	public ServiceResponse<?> createLanguage(@RequestBody Language language) {
		return new ServiceResponse<BaseMessageResponse>(languageService.createNewLanguage(language));
	}

	@GetMapping(value = "list")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> languageList() {
		return new ServiceResponse<BaseMessageResponse>(languageService.getAllLanguages());
	}

	@DeleteMapping(value = "{languageId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> deleteLanguage(@PathVariable Long languageId) {
		return new ServiceResponse<BaseMessageResponse>(languageService.deleteLanguage(languageId));
	}

	@GetMapping(value = "{languageId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> languageById(@PathVariable Long languageId) {
		return new ServiceResponse<BaseMessageResponse>(languageService.languageById(languageId));
	}

	@PutMapping(value = "{languageId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> updateLanguage(@PathVariable Long languageId, @RequestBody LanguageDto language) {
		language.setId(languageId);
		return new ServiceResponse<BaseMessageResponse>(languageService.updateLanguage(language));
	}
}
