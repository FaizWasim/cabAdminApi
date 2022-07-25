package com.booking.yoya.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.booking.yoya.model.common.SharedEntity;

@Entity
@Table(name = "vehicle_normal_price")
//@Where(clause = "is_active = true")
public class VehicleNormalPrice extends SharedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "process_id", nullable = false)
	private Long processId;

	@Column(name = "country_id", nullable = false)
	private Long countryId;

	@Column(name = "state_id", nullable = false)
	private Long stateId;

	@Column(name = "city_id", nullable = false)
	private Long cityId;

	@Column(name = "vehicle_id", nullable = false)
	private Long vehicleId;

	private String baseFare;
	private String normalHoursPricing;
	private String peakHoursPricing;
	private String freeWaitingTime;
	private String waitingCharges;
	private String stoppageCharges;
	private String adminCommision;

	public VehicleNormalPrice() {
		// TODO Auto-generated constructor stub
	}

	public VehicleNormalPrice(Long id, Long processId, Long countryId, Long stateId, Long cityId, Long vehicleId,
			String baseFare, String normalHoursPricing, String peakHoursPricing, String freeWaitingTime,
			String waitingCharges, String stoppageCharges, String adminCommision) {
		super();
		this.id = id;
		this.processId = processId;
		this.countryId = countryId;
		this.stateId = stateId;
		this.cityId = cityId;
		this.vehicleId = vehicleId;
		this.baseFare = baseFare;
		this.normalHoursPricing = normalHoursPricing;
		this.peakHoursPricing = peakHoursPricing;
		this.freeWaitingTime = freeWaitingTime;
		this.waitingCharges = waitingCharges;
		this.stoppageCharges = stoppageCharges;
		this.adminCommision = adminCommision;
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

	@Override
	public String toString() {
		return "VehicleNormalPrice [id=" + id + ", processId=" + processId + ", countryId=" + countryId + ", stateId="
				+ stateId + ", cityId=" + cityId + ", vehicleId=" + vehicleId + ", baseFare=" + baseFare
				+ ", normalHoursPricing=" + normalHoursPricing + ", peakHoursPricing=" + peakHoursPricing
				+ ", freeWaitingTime=" + freeWaitingTime + ", waitingCharges=" + waitingCharges + ", stoppageCharges="
				+ stoppageCharges + ", adminCommision=" + adminCommision + "]";
	}

}
