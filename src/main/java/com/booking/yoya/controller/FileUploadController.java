package com.booking.yoya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.FileUploadService;

@RestController
@RequestMapping("api/v1")
public class FileUploadController {

	@Autowired
	private FileUploadService fileUploadService;

	@PostMapping(value = "/driver/upload-file")
	@PreAuthorize("hasRole('Driver')")
	public ServiceResponse<?> uploadFile(@RequestParam("file") MultipartFile file) {
		return new ServiceResponse<BaseMessageResponse>(fileUploadService.fileUpload(file));
	}

}
