package com.booking.yoya.controller.jwt;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.booking.yoya.constant.StringsConstant;
import com.booking.yoya.model.jwt.JwtRequest;
import com.booking.yoya.model.jwt.JwtResponse;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.aws.s3.AmazonS3ClientService;
import com.booking.yoya.service.jwt.JwtService;

@RestController
@CrossOrigin
public class JwtController {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AmazonS3ClientService amazonS3ClientService;

	@PostMapping({ "/authenticate" })
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		return jwtService.createJwtToken(jwtRequest);
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ServiceResponse<BaseMessageResponse> uploadFile(@RequestPart(value = "file") MultipartFile file) {

		System.out.println("get data..");

		System.out.println("get data of file.." + file.getOriginalFilename());
		String icons = StringsConstant.DOCUMENTS.value;
		UUID randomUUID = UUID.randomUUID();
		boolean status = this.amazonS3ClientService.uploadFileToS3Bucket(file, true, icons, randomUUID);
		Map<String, String> response = new HashMap<>();
		if (status) {
			response.put("message",
					"file [" + file.getOriginalFilename() + "] uploading request submitted successfully.");
		} else {
			response.put("message", "unable to upload request.");
		}

		return new ServiceResponse<BaseMessageResponse>(
				BaseMessageResponse.builder().message("success").status(true).data(response).build(), HttpStatus.OK);
	}
}