package com.booking.yoya.dto;

public class PushNotificationDto {

	private Long id;
	private Long sendTo;
	private Long countryId;
	private Long stateId;
	private Long cityId;
	private Long processId;
	private Long vehicleId;

	private String countryName;
	private String stateName;
	private String cityName;
	private String processName;
	private String vehicleName;

	private String subject;
	private String text;
	private String image;
	private int isActive;

	public PushNotificationDto() {
		// TODO Auto-generated constructor stub
	}

	public PushNotificationDto(Long id, Long sendTo, Long countryId, Long stateId, Long cityId, Long processId,
			Long vehicleId, String countryName, String stateName, String cityName, String processName,
			String vehicleName, String subject, String text, String image, int isActive) {
		super();
		this.id = id;
		this.sendTo = sendTo;
		this.countryId = countryId;
		this.stateId = stateId;
		this.cityId = cityId;
		this.processId = processId;
		this.vehicleId = vehicleId;
		this.countryName = countryName;
		this.stateName = stateName;
		this.cityName = cityName;
		this.processName = processName;
		this.vehicleName = vehicleName;
		this.subject = subject;
		this.text = text;
		this.image = image;
		this.isActive = isActive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSendTo() {
		return sendTo;
	}

	public void setSendTo(Long sendTo) {
		this.sendTo = sendTo;
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

	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "PushNotificationDto [id=" + id + ", sendTo=" + sendTo + ", countryId=" + countryId + ", stateId="
				+ stateId + ", cityId=" + cityId + ", processId=" + processId + ", vehicleId=" + vehicleId
				+ ", countryName=" + countryName + ", stateName=" + stateName + ", cityName=" + cityName
				+ ", processName=" + processName + ", vehicleName=" + vehicleName + ", subject=" + subject + ", text="
				+ text + ", image=" + image + ", isActive=" + isActive + "]";
	}

}
