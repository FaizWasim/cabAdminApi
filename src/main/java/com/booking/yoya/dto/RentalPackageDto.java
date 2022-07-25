package com.booking.yoya.dto;

public class RentalPackageDto {

	private Long id;

	private Long processId;

	private Long countryId;

	private Long stateId;

	private Long cityId;

	private Long vehicleId;

	private String countryName;
	private String stateName;
	private String cityName;
	private String processName;
	private String vehicleName;

	private String hr;
	private String km;
	private int isActive;

	public RentalPackageDto() {
		// TODO Auto-generated constructor stub
	}

	public RentalPackageDto(Long id, Long processId, Long countryId, Long stateId, Long cityId, Long vehicleId,
			String countryName, String stateName, String cityName, String processName, String vehicleName, String hr,
			String km, int isActive) {
		super();
		this.id = id;
		this.processId = processId;
		this.countryId = countryId;
		this.stateId = stateId;
		this.cityId = cityId;
		this.vehicleId = vehicleId;
		this.countryName = countryName;
		this.stateName = stateName;
		this.cityName = cityName;
		this.processName = processName;
		this.vehicleName = vehicleName;
		this.hr = hr;
		this.km = km;
		this.isActive = isActive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
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

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
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

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getHr() {
		return hr;
	}

	public void setHr(String hr) {
		this.hr = hr;
	}

	public String getKm() {
		return km;
	}

	public void setKm(String km) {
		this.km = km;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "RentalPackageDto [id=" + id + ", processId=" + processId + ", countryId=" + countryId + ", stateId="
				+ stateId + ", cityId=" + cityId + ", vehicleId=" + vehicleId + ", countryName=" + countryName
				+ ", stateName=" + stateName + ", cityName=" + cityName + ", processName=" + processName
				+ ", vehicleName=" + vehicleName + ", hr=" + hr + ", km=" + km + ", isActive=" + isActive + "]";
	}

}
