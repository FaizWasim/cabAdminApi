package com.booking.yoya.dto;

import lombok.Data;

@Data
public class WalletDto {

	private Long id;

	private float walletAmount;
	private Long walletCustomerId;
	private Long walletDriverId;
	private String walletTransactionType;
	private Long walletTransactionId;
	private String walletDescription;
	private String walletDateTime;
	private String walletType;
	private String walletStatus;
	private String walletUseFor;

}
