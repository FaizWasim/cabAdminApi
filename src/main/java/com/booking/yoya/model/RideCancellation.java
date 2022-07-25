package com.booking.yoya.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.booking.yoya.model.common.SharedEntity;

@Entity
@Table(name = "ride_cancellation")
//@Where(clause = "is_active = true")
public class RideCancellation extends SharedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "reason_for", nullable = false)
	private String reasonFor;

	private String cancellation;

	public RideCancellation() {
		// TODO Auto-generated constructor stub
	}

	public RideCancellation(Long id, String reasonFor, String cancellation) {
		super();
		this.id = id;
		this.reasonFor = reasonFor;
		this.cancellation = cancellation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReasonFor() {
		return reasonFor;
	}

	public void setReasonFor(String reasonFor) {
		this.reasonFor = reasonFor;
	}

	public String getCancellation() {
		return cancellation;
	}

	public void setCancellation(String cancellation) {
		this.cancellation = cancellation;
	}

	@Override
	public String toString() {
		return "RideCancellation [id=" + id + ", reasonFor=" + reasonFor + ", cancellation=" + cancellation + "]";
	}

}
