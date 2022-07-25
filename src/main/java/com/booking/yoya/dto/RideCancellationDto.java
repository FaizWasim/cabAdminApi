package com.booking.yoya.dto;

public class RideCancellationDto {

	private Long id;

	private String reasonFor;

	private String cancellation;
	private int isActive;

	public RideCancellationDto() {
		// TODO Auto-generated constructor stub
	}

	public RideCancellationDto(Long id, String reasonFor, String cancellation, int isActive) {
		super();
		this.id = id;
		this.reasonFor = reasonFor;
		this.cancellation = cancellation;
		this.isActive = isActive;
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "RideCancellationDto [id=" + id + ", reasonFor=" + reasonFor + ", cancellation=" + cancellation
				+ ", isActive=" + isActive + "]";
	}

}
