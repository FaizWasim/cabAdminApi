package com.booking.yoya.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.booking.yoya.model.common.SharedEntity;

@Entity
@Table(name = "city")
//@Where(clause = "is_active = true")
public class City extends SharedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "country_id", nullable = false)
	private Long countryId;

	@Column(name = "state_id", nullable = false)
	private Long stateId;

	@Column(name = "city_name", nullable = false)
	private String cityName;

	public City() {
		// TODO Auto-generated constructor stub
	}

	public City(Long id, Long countryId, Long stateId, String cityName) {
		super();
		this.id = id;
		this.countryId = countryId;
		this.stateId = stateId;
		this.cityName = cityName;
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

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", countryId=" + countryId + ", stateId=" + stateId + ", cityName=" + cityName + "]";
	}

}
