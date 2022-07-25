package com.booking.yoya.dto;

public class ProcessDto {

	private Long id;

	private Long countryId;

	private Long stateId;

	private Long cityId;

	private String countryName;
	private String stateName;
	private String cityName;

	private String name;

	private String icon;
	private int isActive;

	public ProcessDto() {
		// TODO Auto-generated constructor stub
	}

	public ProcessDto(Long id, Long countryId, Long stateId, Long cityId, String countryName, String stateName,
			String cityName, String name, String icon, int isActive) {
		super();
		this.id = id;
		this.countryId = countryId;
		this.stateId = stateId;
		this.cityId = cityId;
		this.countryName = countryName;
		this.stateName = stateName;
		this.cityName = cityName;
		this.name = name;
		this.icon = icon;
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

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "ProcessDto [id=" + id + ", countryId=" + countryId + ", stateId=" + stateId + ", cityId=" + cityId
				+ ", countryName=" + countryName + ", stateName=" + stateName + ", cityName=" + cityName + ", name="
				+ name + ", icon=" + icon + ", isActive=" + isActive + "]";
	}

}
