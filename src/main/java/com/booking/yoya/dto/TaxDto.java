package com.booking.yoya.dto;

public class TaxDto {

	private Long id;

	private Long countryId;

	private Long stateId;

	private String countryName;
	private String stateName;
	private String taxName;

	private String taxPrice;
	private int isActive;

	public TaxDto() {
		// TODO Auto-generated constructor stub
	}

	public TaxDto(Long id, Long countryId, Long stateId, String countryName, String stateName, String taxName,
			String taxPrice, int isActive) {
		super();
		this.id = id;
		this.countryId = countryId;
		this.stateId = stateId;
		this.countryName = countryName;
		this.stateName = stateName;
		this.taxName = taxName;
		this.taxPrice = taxPrice;
		this.isActive = isActive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public String getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(String taxPrice) {
		this.taxPrice = taxPrice;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@Override
	public String toString() {
		return "TaxDto [id=" + id + ", countryId=" + countryId + ", stateId=" + stateId + ", countryName=" + countryName
				+ ", stateName=" + stateName + ", taxName=" + taxName + ", taxPrice=" + taxPrice + ", isActive="
				+ isActive + "]";
	}

}
