package com.booking.yoya.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.booking.yoya.model.common.SharedEntity;

@Entity
@Table(name = "wallet")
//@Where(clause = "is_active = true")
public class Wallet extends SharedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
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

	public Wallet() {
		// TODO Auto-generated constructor stub
	}

	public Wallet(Long id, float walletAmount, Long walletCustomerId, Long walletDriverId, String walletTransactionType,
			Long walletTransactionId, String walletDescription, String walletDateTime, String walletType,
			String walletStatus, String walletUseFor) {
		super();
		this.id = id;
		this.walletAmount = walletAmount;
		this.walletCustomerId = walletCustomerId;
		this.walletDriverId = walletDriverId;
		this.walletTransactionType = walletTransactionType;
		this.walletTransactionId = walletTransactionId;
		this.walletDescription = walletDescription;
		this.walletDateTime = walletDateTime;
		this.walletType = walletType;
		this.walletStatus = walletStatus;
		this.walletUseFor = walletUseFor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getWalletAmount() {
		return walletAmount;
	}

	public void setWalletAmount(float walletAmount) {
		this.walletAmount = walletAmount;
	}

	public Long getWalletCustomerId() {
		return walletCustomerId;
	}

	public void setWalletCustomerId(Long walletCustomerId) {
		this.walletCustomerId = walletCustomerId;
	}

	public Long getWalletDriverId() {
		return walletDriverId;
	}

	public void setWalletDriverId(Long walletDriverId) {
		this.walletDriverId = walletDriverId;
	}

	public String getWalletTransactionType() {
		return walletTransactionType;
	}

	public void setWalletTransactionType(String walletTransactionType) {
		this.walletTransactionType = walletTransactionType;
	}

	public Long getWalletTransactionId() {
		return walletTransactionId;
	}

	public void setWalletTransactionId(Long walletTransactionId) {
		this.walletTransactionId = walletTransactionId;
	}

	public String getWalletDescription() {
		return walletDescription;
	}

	public void setWalletDescription(String walletDescription) {
		this.walletDescription = walletDescription;
	}

	public String getWalletDateTime() {
		return walletDateTime;
	}

	public void setWalletDateTime(String walletDateTime) {
		this.walletDateTime = walletDateTime;
	}

	public String getWalletType() {
		return walletType;
	}

	public void setWalletType(String walletType) {
		this.walletType = walletType;
	}

	public String getWalletStatus() {
		return walletStatus;
	}

	public void setWalletStatus(String walletStatus) {
		this.walletStatus = walletStatus;
	}

	public String getWalletUseFor() {
		return walletUseFor;
	}

	public void setWalletUseFor(String walletUseFor) {
		this.walletUseFor = walletUseFor;
	}

	@Override
	public String toString() {
		return "Wallet [id=" + id + ", walletAmount=" + walletAmount + ", walletCustomerId=" + walletCustomerId
				+ ", walletDriverId=" + walletDriverId + ", walletTransactionType=" + walletTransactionType
				+ ", walletTransactionId=" + walletTransactionId + ", walletDescription=" + walletDescription
				+ ", walletDateTime=" + walletDateTime + ", walletType=" + walletType + ", walletStatus=" + walletStatus
				+ ", walletUseFor=" + walletUseFor + "]";
	}

}
