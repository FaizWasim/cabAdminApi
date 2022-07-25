package com.booking.yoya.dto;

public class LanguageDto {

	private Long id;
	private Long countryId;
	private String countryName;
	private String languageName;
	private String languageCode;
	private int isActive;

	public LanguageDto() {
		// TODO Auto-generated constructor stub
	}

	public LanguageDto(Long id, Long countryId, String countryName, String languageName, String languageCode,
			int isActive) {
		super();
		this.id = id;
		this.countryId = countryId;
		this.countryName = countryName;
		this.languageName = languageName;
		this.languageCode = languageCode;
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

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "LanguageDto [id=" + id + ", countryId=" + countryId + ", countryName=" + countryName + ", languageName="
				+ languageName + ", languageCode=" + languageCode + ", isActive=" + isActive + "]";
	}

}
