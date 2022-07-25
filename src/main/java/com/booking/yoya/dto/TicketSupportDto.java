package com.booking.yoya.dto;

import lombok.Data;

@Data
public class TicketSupportDto {

	private Long ticketId;

	private String uniqueId;

	private Long userId;

	private Long driverId;

	private String subject;

	private String ticketType;

	private String ticketStatus;
}
