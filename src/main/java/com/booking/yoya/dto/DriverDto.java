package com.booking.yoya.dto;

import lombok.Data;

@Data
public class DriverDto {

	private Long id;
	private Long countryId;
	private Long stateId;
	private Long cityId;
	private String countryName;
	private String stateName;
	private String cityName;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String password;
	private String driverImage;
	private String driverDescription;
	private String driverStatus;

	private String driverDeviceId;
	private String driverDeviceName;
	private String driverDeviceType;
	private String driverDeviceToken;

	private String driverOtp;
	private String driverReferralCode;
	private String driverReferralBy;
	private String driverLoginStatus;
	private String driverRideTakingStatus;
	private Boolean isActive;

}
