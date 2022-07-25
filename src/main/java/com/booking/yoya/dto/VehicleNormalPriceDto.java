package com.booking.yoya.dto;

public class VehicleNormalPriceDto {

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

	private String baseFare;
	private String normalHoursPricing;
	private String peakHoursPricing;
	private String freeWaitingTime;
	private String waitingCharges;
	private String stoppageCharges;
	private String adminCommision;
	private int isActive;

	public VehicleNormalPriceDto() {
		// TODO Auto-generated constructor stub
	}

	public VehicleNormalPriceDto(Long id, Long processId, Long countryId, Long stateId, Long cityId, Long vehicleId,
			String countryName, String stateName, String cityName, String processName, String vehicleName,
			String baseFare, String normalHoursPricing, String peakHoursPricing, String freeWaitingTime,
			String waitingCharges, String stoppageCharges, String adminCommision, int isActive) {
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
		this.baseFare = baseFare;
		this.normalHoursPricing = normalHoursPricing;
		this.peakHoursPricing = peakHoursPricing;
		this.freeWaitingTime = freeWaitingTime;
		this.waitingCharges = waitingCharges;
		this.stoppageCharges = stoppageCharges;
		this.adminCommision = adminCommision;
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

	public String getBaseFare() {
		return baseFare;
	}

	public void setBaseFare(String baseFare) {
		this.baseFare = baseFare;
	}

	public String getNormalHoursPricing() {
		return normalHoursPricing;
	}

	public void setNormalHoursPricing(String normalHoursPricing) {
		this.normalHoursPricing = normalHoursPricing;
	}

	public String getPeakHoursPricing() {
		return peakHoursPricing;
	}

	public void setPeakHoursPricing(String peakHoursPricing) {
		this.peakHoursPricing = peakHoursPricing;
	}

	public String getFreeWaitingTime() {
		return freeWaitingTime;
	}

	public void setFreeWaitingTime(String freeWaitingTime) {
		this.freeWaitingTime = freeWaitingTime;
	}

	public String getWaitingCharges() {
		return waitingCharges;
	}

	public void setWaitingCharges(String waitingCharges) {
		this.waitingCharges = waitingCharges;
	}

	public String getStoppageCharges() {
		return stoppageCharges;
	}

	public void setStoppageCharges(String stoppageCharges) {
		this.stoppageCharges = stoppageCharges;
	}

	public String getAdminCommision() {
		return adminCommision;
	}

	public void setAdminCommision(String adminCommision) {
		this.adminCommision = adminCommision;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "VehicleNormalPriceDto [id=" + id + ", processId=" + processId + ", countryId=" + countryId
				+ ", stateId=" + stateId + ", cityId=" + cityId + ", vehicleId=" + vehicleId + ", countryName="
				+ countryName + ", stateName=" + stateName + ", cityName=" + cityName + ", processName=" + processName
				+ ", vehicleName=" + vehicleName + ", baseFare=" + baseFare + ", normalHoursPricing="
				+ normalHoursPricing + ", peakHoursPricing=" + peakHoursPricing + ", freeWaitingTime=" + freeWaitingTime
				+ ", waitingCharges=" + waitingCharges + ", stoppageCharges=" + stoppageCharges + ", adminCommision="
				+ adminCommision + ", isActive=" + isActive + "]";
	}

}
