package com.booking.yoya.dto;

import lombok.Data;

@Data
public class TicketSupportReplyDto {

	private Long ticketChatId;

	private Long ticketId;

	private String ticketType;

	private String message;

}
