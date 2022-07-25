package com.booking.yoya.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.booking.yoya.model.common.SharedEntity;

@Entity
@Table(name = "tax")
//@Where(clause = "is_active = true")
public class Tax extends SharedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "country_id", nullable = false)
	private Long countryId;

	@Column(name = "state_id", nullable = false)
	private Long stateId;

	@Column(name = "tax_name", nullable = false)
	private String taxName;

	@Column(name = "tax_price", nullable = false)
	private String taxPrice;

	public Tax() {
		// TODO Auto-generated constructor stub
	}

	public Tax(Long id, Long countryId, Long stateId, String taxName, String taxPrice) {
		super();
		this.id = id;
		this.countryId = countryId;
		this.stateId = stateId;
		this.taxName = taxName;
		this.taxPrice = taxPrice;
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

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public String getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(String taxPrice) {
		this.taxPrice = taxPrice;
	}

	@Override
	public String toString() {
		return "Tax [id=" + id + ", countryId=" + countryId + ", stateId=" + stateId + ", taxName=" + taxName
				+ ", taxPrice=" + taxPrice + "]";
	}

}
