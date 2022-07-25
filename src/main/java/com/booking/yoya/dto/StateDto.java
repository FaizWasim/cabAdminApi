package com.booking.yoya.dto;

public class StateDto {

	private Long id;

	private Long countryId;
	private String stateName;
	private String countryName;
	private int isActive;

	public StateDto() {
		// TODO Auto-generated constructor stub
	}

	public StateDto(Long id, Long countryId, String stateName, int isActive, String countryName) {
		super();
		this.id = id;
		this.countryId = countryId;
		this.stateName = stateName;
		this.isActive = isActive;
		this.countryName = countryName;
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

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
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

	@Override
	public String toString() {
		return "StateDto [id=" + id + ", countryId=" + countryId + ", stateName=" + stateName + ", countryName="
				+ countryName + ", isActive=" + isActive + "]";
	}

}
