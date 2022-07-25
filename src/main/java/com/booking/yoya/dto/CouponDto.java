package com.booking.yoya.dto;

public class CouponDto {

	private Long id;
	private Long countryId;
	private Long stateId;
	private Long cityId;
	private Long processId;
	private Long vehicleId;
	private Long userId;
	private String countryName;
	private String stateName;
	private String cityName;
	private String processName;
	private String vehicleName;
	private String userName;
	private String couponName;
	private String couponCode;

	private Long isAutoApply;
	private Long discountType;
	private Long couponType;

	private int maxUserUses;
	private Float minAmount;
	private Float maxAmountOfDiscount;
	private String startDate;
	private String endDate;
	private int isActive;
	private Float amount;

	public CouponDto() {
		// TODO Auto-generated constructor stub
	}

	public CouponDto(Long id, Long countryId, Long stateId, Long cityId, Long processId, Long vehicleId, Long userId,
			String countryName, String stateName, String cityName, String processName, String vehicleName,
			String userName, String couponName, String couponCode, Long isAutoApply, Long discountType, Long couponType,
			int maxUserUses, Float minAmount, Float maxAmountOfDiscount, String startDate, String endDate, int isActive,
			Float amount) {
		super();
		this.id = id;
		this.countryId = countryId;
		this.stateId = stateId;
		this.cityId = cityId;
		this.processId = processId;
		this.vehicleId = vehicleId;
		this.userId = userId;
		this.countryName = countryName;
		this.stateName = stateName;
		this.cityName = cityName;
		this.processName = processName;
		this.vehicleName = vehicleName;
		this.userName = userName;
		this.couponName = couponName;
		this.couponCode = couponCode;
		this.isAutoApply = isAutoApply;
		this.discountType = discountType;
		this.couponType = couponType;
		this.maxUserUses = maxUserUses;
		this.minAmount = minAmount;
		this.maxAmountOfDiscount = maxAmountOfDiscount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isActive = isActive;
		this.amount = amount;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Long getIsAutoApply() {
		return isAutoApply;
	}

	public void setIsAutoApply(Long isAutoApply) {
		this.isAutoApply = isAutoApply;
	}

	public Long getDiscountType() {
		return discountType;
	}

	public void setDiscountType(Long discountType) {
		this.discountType = discountType;
	}

	public Long getCouponType() {
		return couponType;
	}

	public void setCouponType(Long couponType) {
		this.couponType = couponType;
	}

	public int getMaxUserUses() {
		return maxUserUses;
	}

	public void setMaxUserUses(int maxUserUses) {
		this.maxUserUses = maxUserUses;
	}

	public Float getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(Float minAmount) {
		this.minAmount = minAmount;
	}

	public Float getMaxAmountOfDiscount() {
		return maxAmountOfDiscount;
	}

	public void setMaxAmountOfDiscount(Float maxAmountOfDiscount) {
		this.maxAmountOfDiscount = maxAmountOfDiscount;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "CouponDto [id=" + id + ", countryId=" + countryId + ", stateId=" + stateId + ", cityId=" + cityId
				+ ", processId=" + processId + ", vehicleId=" + vehicleId + ", userId=" + userId + ", countryName="
				+ countryName + ", stateName=" + stateName + ", cityName=" + cityName + ", processName=" + processName
				+ ", vehicleName=" + vehicleName + ", userName=" + userName + ", couponName=" + couponName
				+ ", couponCode=" + couponCode + ", isAutoApply=" + isAutoApply + ", discountType=" + discountType
				+ ", couponType=" + couponType + ", maxUserUses=" + maxUserUses + ", minAmount=" + minAmount
				+ ", maxAmountOfDiscount=" + maxAmountOfDiscount + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", isActive=" + isActive + ", amount=" + amount + "]";
	}

}
