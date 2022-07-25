package com.booking.yoya.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class VehicleDto {

	private Long id;

	private Long processId;

	private Long countryId;

	private Long stateId;

	private Long cityId;

	private String countryName;
	private String stateName;
	private String cityName;
	private String processName;

	private String name;

	private MultipartFile icon;
	private String iconPath;

	private String vehicleDescription;
	private int isActive;

}
