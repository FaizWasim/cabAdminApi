package com.booking.yoya.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ContentDto {

	private Long contentId;

	private String contentTitle;

	private String contentDescription;

	private MultipartFile contentImage;

	private String contentImagePath;

}
