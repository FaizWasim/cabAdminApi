package com.booking.yoya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.booking.yoya.helper.FileUploadHelper;
import com.booking.yoya.model.City;
import com.booking.yoya.response.BaseMessageResponse;

@Service
public class FileUploadService {

	@Autowired
	private FileUploadHelper fileUploadHelper;

	public BaseMessageResponse fileUpload(MultipartFile file) {
		if (file.isEmpty()) {
			return BaseMessageResponse.builder().message("success").status(false).data("Request must contain the file")
					.build();
		}
//		file upload code..
		boolean fileUploaded = fileUploadHelper.uploadFile(file);
		if (fileUploaded)
			return BaseMessageResponse.builder().message("success").status(true).data("File uploaded successfully")
					.build();
		else
			return BaseMessageResponse.builder().message("success").status(false).data("Upload failed").build();
	}
}
