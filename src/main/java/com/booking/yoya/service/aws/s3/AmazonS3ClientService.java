package com.booking.yoya.service.aws.s3;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public interface AmazonS3ClientService {
	boolean uploadFileToS3Bucket(MultipartFile multipartFile, boolean enablePublicReadAccess, String folder,
			UUID randomUUID);

}
