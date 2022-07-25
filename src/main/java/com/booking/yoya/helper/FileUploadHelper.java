package com.booking.yoya.helper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

	public final String UPLOAD_DIR = "C:\\home\\ubuntu";

	public boolean uploadFile(MultipartFile file) {
		boolean upload = false;
		try {
			Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			upload = true;
		} catch (Exception e) {
			e.printStackTrace();
			upload = false;
		}
		return upload;
	}

}
