package com.booking.yoya.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.booking.yoya.model.common.SharedEntity;

import lombok.Data;

@Entity
@Table(name = "driver")
//@Where(clause = "is_active = true")
@Data
public class Driver extends SharedEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	private String userName;
	private String phoneNumber;

	private Long countryId;
	private Long stateId;
	private Long cityId;
	private String firstName;
	private String lastName;
	private String email;
	private String driverImage;
	private String driverDescription;
	private String driverStatus;

	@Column(name = "driver_device_id", nullable = false)
	private String driverDeviceId;
	@Column(name = "driver_device_name", nullable = false)
	private String driverDeviceName;
	@Column(name = "driver_device_type", nullable = false)
	private String driverDeviceType;
	@Column(name = "driver_device_token", nullable = false)
	private String driverDeviceToken;

	@Column(name = "driver_otp", nullable = false)
	private String driverOtp;
	private String driverReferralCode;
	private String driverReferralBy;
	private String driverLoginStatus;
	private String driverRideTakingStatus;
	private int driverId;

}
