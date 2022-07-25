package com.booking.yoya.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.booking.yoya.model.common.SharedEntity;

@Entity
@Table(name = "country")
//@Where(clause = "is_active = true")
public class Country extends SharedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	private String countryName;
	private String countryCode;
	private String countryDialingCode;
	private String flag;

	public Country() {
		// TODO Auto-generated constructor stub
	}

	public Country(Long id, String countryName, String countryCode, String countryDialingCode, String flag) {
		super();
		this.id = id;
		this.countryName = countryName;
		this.countryCode = countryCode;
		this.countryDialingCode = countryDialingCode;
		this.flag = flag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryDialingCode() {
		return countryDialingCode;
	}

	public void setCountryDialingCode(String countryDialingCode) {
		this.countryDialingCode = countryDialingCode;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", countryName=" + countryName + ", countryCode=" + countryCode
				+ ", countryDialingCode=" + countryDialingCode + ", flag=" + flag + "]";
	}

}
