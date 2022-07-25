package com.booking.yoya.dto;

public class RatingDto {

	private Long id;

	private Long bookingId;

	private Long userId;

	private Long driverId;

	private String ratingStars;

	private String ratingText;

	private String ratedBy;
	private int isActive;

	public RatingDto() {
		// TODO Auto-generated constructor stub
	}

	public RatingDto(Long id, Long bookingId, Long userId, Long driverId, String ratingStars, String ratingText,
			String ratedBy, int isActive) {
		super();
		this.id = id;
		this.bookingId = bookingId;
		this.userId = userId;
		this.driverId = driverId;
		this.ratingStars = ratingStars;
		this.ratingText = ratingText;
		this.ratedBy = ratedBy;
		this.isActive = isActive;
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "RatingDto [id=" + id + ", bookingId=" + bookingId + ", userId=" + userId + ", driverId=" + driverId
				+ ", ratingStars=" + ratingStars + ", ratingText=" + ratingText + ", ratedBy=" + ratedBy + ", isActive="
				+ isActive + "]";
	}

}
