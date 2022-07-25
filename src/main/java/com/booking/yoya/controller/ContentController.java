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

import com.booking.yoya.dto.ContentDto;
import com.booking.yoya.model.Content;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.ContentService;

@RestController
@RequestMapping("api/v1/content")
public class ContentController {
	@Autowired
	private ContentService contentService;

	@PostMapping(value = "create")
	public ServiceResponse<?> createContent(@ModelAttribute ContentDto content) {
		return new ServiceResponse<BaseMessageResponse>(contentService.createNewContent(content));
	}

	@GetMapping(value = "list")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> contentList() {
		return new ServiceResponse<BaseMessageResponse>(contentService.getAllContents());
	}

	@DeleteMapping(value = "{contentId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> deleteContent(@PathVariable Long contentId) {
		return new ServiceResponse<BaseMessageResponse>(contentService.deleteContent(contentId));
	}

	@GetMapping(value = "{contentId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> contentById(@PathVariable Long contentId) {
		return new ServiceResponse<BaseMessageResponse>(contentService.contentById(contentId));
	}

	@PutMapping(value = "{contentId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> updateContent(@PathVariable Long contentId, @ModelAttribute ContentDto content) {
		content.setContentId(contentId);
		return new ServiceResponse<BaseMessageResponse>(contentService.updateContent(content));
	}
}
