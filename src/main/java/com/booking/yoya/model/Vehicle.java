package com.booking.yoya.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.booking.yoya.model.common.SharedEntity;

@Entity
@Table(name = "vehicle")
//@Where(clause = "is_active = true")
public class Vehicle extends SharedEntity {

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

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "icon", nullable = false)
	private String icon;

	@Column(name = "vehicle_description", nullable = false)
	private String vehicleDescription;

	public Vehicle() {
		// TODO Auto-generated constructor stub
	}

	public Vehicle(Long id, Long processId, Long countryId, Long stateId, Long cityId, String name, String icon,
			String vehicleDescription) {
		super();
		this.id = id;
		this.processId = processId;
		this.countryId = countryId;
		this.stateId = stateId;
		this.cityId = cityId;
		this.name = name;
		this.icon = icon;
		this.vehicleDescription = vehicleDescription;
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

	public String getVehicleDescription() {
		return vehicleDescription;
	}

	public void setVehicleDescription(String vehicleDescription) {
		this.vehicleDescription = vehicleDescription;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", processId=" + processId + ", countryId=" + countryId + ", stateId=" + stateId
				+ ", cityId=" + cityId + ", name=" + name + ", icon=" + icon + ", vehicleDescription="
				+ vehicleDescription + "]";
	}

}
