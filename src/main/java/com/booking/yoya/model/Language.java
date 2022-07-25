package com.booking.yoya.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.booking.yoya.model.common.SharedEntity;

@Entity
@Table(name = "language")
//@Where(clause = "is_active = true")
public class Language extends SharedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "country_id", nullable = false)
	private Long countryId;

	@Column(name = "language_name", nullable = false)
	private String languageName;

	@Column(name = "language_code", nullable = false)
	private String languageCode;

	public Language() {
		// TODO Auto-generated constructor stub
	}

	public Language(Long id, Long countryId, String languageName, String languageCode) {
		super();
		this.id = id;
		this.countryId = countryId;
		this.languageName = languageName;
		this.languageCode = languageCode;
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

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	@Override
	public String toString() {
		return "Language [id=" + id + ", countryId=" + countryId + ", languageName=" + languageName + ", languageCode="
				+ languageCode + "]";
	}

}
