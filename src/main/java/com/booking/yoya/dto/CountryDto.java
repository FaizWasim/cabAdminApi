package com.booking.yoya.dto;

public class CountryDto {

	private Long id;

	private String countryName;
	private String countryCode;
	private String countryDialingCode;
	private String flag;
	private int isActive;

	public CountryDto() {
		// TODO Auto-generated constructor stub
	}

	public CountryDto(Long id, String countryName, String countryCode, String countryDialingCode, String flag,
			int isActive) {
		super();
		this.id = id;
		this.countryName = countryName;
		this.countryCode = countryCode;
		this.countryDialingCode = countryDialingCode;
		this.flag = flag;
		this.isActive = isActive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryDialingCode() {
		return countryDialingCode;
	}

	public void setCountryDialingCode(String countryDialingCode) {
		this.countryDialingCode = countryDialingCode;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "CountryDto [id=" + id + ", countryName=" + countryName + ", countryCode=" + countryCode
				+ ", countryDialingCode=" + countryDialingCode + ", flag=" + flag + ", isActive=" + isActive + "]";
	}

}
