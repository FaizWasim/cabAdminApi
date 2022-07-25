package com.booking.yoya.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.booking.yoya.model.common.SharedEntity;

@Entity
@Table(name = "rating")
//@Where(clause = "is_active = true")
public class Rating extends SharedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "booking_id", nullable = false)
	private Long bookingId;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "driver_id", nullable = false)
	private Long driverId;

	private String ratingStars;

	private String ratingText;

	private String ratedBy;

	public Rating() {
		// TODO Auto-generated constructor stub
	}

	public Rating(Long id, Long bookingId, Long userId, Long driverId, String ratingStars, String ratingText,
			String ratedBy) {
		super();
		this.id = id;
		this.bookingId = bookingId;
		this.userId = userId;
		this.driverId = driverId;
		this.ratingStars = ratingStars;
		this.ratingText = ratingText;
		this.ratedBy = ratedBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public String getRatingStars() {
		return ratingStars;
	}

	public void setRatingStars(String ratingStars) {
		this.ratingStars = ratingStars;
	}

	public String getRatingText() {
		return ratingText;
	}

	public void setRatingText(String ratingText) {
		this.ratingText = ratingText;
	}

	public String getRatedBy() {
		return ratedBy;
	}

	public void setRatedBy(String ratedBy) {
		this.ratedBy = ratedBy;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", bookingId=" + bookingId + ", userId=" + userId + ", driverId=" + driverId
				+ ", ratingStars=" + ratingStars + ", ratingText=" + ratingText + ", ratedBy=" + ratedBy + "]";
	}

}
