package com.booking.yoya.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.booking.yoya.model.common.SharedEntity;

@Entity
@Table(name = "vehicle_outstation_price")
//@Where(clause = "is_active = true")
public class VehicleOutstationPrice extends SharedEntity {

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

	private String oneWayPerKmPrice;
	private String returnWayPerKmPrice;

	public VehicleOutstationPrice() {
		// TODO Auto-generated constructor stub
	}

	public VehicleOutstationPrice(Long id, Long processId, Long countryId, Long stateId, Long cityId, Long vehicleId,
			String oneWayPerKmPrice, String returnWayPerKmPrice) {
		super();
		this.id = id;
		this.processId = processId;
		this.countryId = countryId;
		this.stateId = stateId;
		this.cityId = cityId;
		this.vehicleId = vehicleId;
		this.oneWayPerKmPrice = oneWayPerKmPrice;
		this.returnWayPerKmPrice = returnWayPerKmPrice;
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

	public String getOneWayPerKmPrice() {
		return oneWayPerKmPrice;
	}

	public void setOneWayPerKmPrice(String oneWayPerKmPrice) {
		this.oneWayPerKmPrice = oneWayPerKmPrice;
	}

	public String getReturnWayPerKmPrice() {
		return returnWayPerKmPrice;
	}

	public void setReturnWayPerKmPrice(String returnWayPerKmPrice) {
		this.returnWayPerKmPrice = returnWayPerKmPrice;
	}

	@Override
	public String toString() {
		return "VehicleOutstationPrice [id=" + id + ", processId=" + processId + ", countryId=" + countryId
				+ ", stateId=" + stateId + ", cityId=" + cityId + ", vehicleId=" + vehicleId + ", oneWayPerKmPrice="
				+ oneWayPerKmPrice + ", returnWayPerKmPrice=" + returnWayPerKmPrice + "]";
	}

}
