package com.booking.yoya.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.booking.yoya.model.common.SharedEntity;

@Entity
@Table(name = "coupon")
//@Where(clause = "is_active = true")
public class Coupon extends SharedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "country_id", nullable = false)
	private Long countryId;

	@Column(name = "state_id", nullable = false)
	private Long stateId;

	@Column(name = "city_id", nullable = false)
	private Long cityId;

	@Column(name = "process_id", nullable = false)
	private Long processId;

	@Column(name = "vehicle_id", nullable = false)
	private Long vehicleId;
	@Column(name = "user_id", nullable = false)
	private Long userId;

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
	private Float amount;

	public Coupon() {
		// TODO Auto-generated constructor stub
	}

	public Coupon(Long id, Long countryId, Long stateId, Long cityId, Long processId, Long vehicleId, Long userId,
			String couponName, String couponCode, Long isAutoApply, Long discountType, Long couponType, int maxUserUses,
			Float minAmount, Float maxAmountOfDiscount, String startDate, String endDate, Float amount) {
		super();
		this.id = id;
		this.countryId = countryId;
		this.stateId = stateId;
		this.cityId = cityId;
		this.processId = processId;
		this.vehicleId = vehicleId;
		this.userId = userId;
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

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", countryId=" + countryId + ", stateId=" + stateId + ", cityId=" + cityId
				+ ", processId=" + processId + ", vehicleId=" + vehicleId + ", userId=" + userId + ", couponName="
				+ couponName + ", couponCode=" + couponCode + ", isAutoApply=" + isAutoApply + ", discountType="
				+ discountType + ", couponType=" + couponType + ", maxUserUses=" + maxUserUses + ", minAmount="
				+ minAmount + ", maxAmountOfDiscount=" + maxAmountOfDiscount + ", startDate=" + startDate + ", endDate="
				+ endDate + ", amount=" + amount + "]";
	}

}
