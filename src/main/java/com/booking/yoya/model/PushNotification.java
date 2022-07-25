package com.booking.yoya.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.booking.yoya.model.common.SharedEntity;

@Entity
@Table(name = "push_notification")
//@Where(clause = "is_active = true")
public class PushNotification extends SharedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "send_to", nullable = false)
	private Long sendTo;

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

	private String subject;
	private String text;
	private String image;

	public PushNotification() {
		// TODO Auto-generated constructor stub
	}

	public PushNotification(Long id, Long sendTo, Long countryId, Long stateId, Long cityId, Long processId,
			Long vehicleId, String subject, String text, String image) {
		super();
		this.id = id;
		this.sendTo = sendTo;
		this.countryId = countryId;
		this.stateId = stateId;
		this.cityId = cityId;
		this.processId = processId;
		this.vehicleId = vehicleId;
		this.subject = subject;
		this.text = text;
		this.image = image;
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

	@Override
	public String toString() {
		return "PushNotification [id=" + id + ", sendTo=" + sendTo + ", countryId=" + countryId + ", stateId=" + stateId
				+ ", cityId=" + cityId + ", processId=" + processId + ", vehicleId=" + vehicleId + ", subject="
				+ subject + ", text=" + text + ", image=" + image + "]";
	}

}
