package com.booking.yoya.dto;

public class CityDto {

	private Long id;

	private Long countryId;
	private Long stateId;
	private String cityName;
	private String stateName;
	private String countryName;
	private int isActive;

	public CityDto() {
		// TODO Auto-generated constructor stub
	}

	public CityDto(Long id, Long countryId, Long stateId, String cityName, String stateName, String countryName,
			int isActive) {
		super();
		this.id = id;
		this.countryId = countryId;
		this.stateId = stateId;
		this.cityName = cityName;
		this.stateName = stateName;
		this.countryName = countryName;
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

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public String toString() {
		return "CityDto [id=" + id + ", countryId=" + countryId + ", stateId=" + stateId + ", cityName=" + cityName
				+ ", stateName=" + stateName + ", countryName=" + countryName + ", isActive=" + isActive + "]";
	}

}
