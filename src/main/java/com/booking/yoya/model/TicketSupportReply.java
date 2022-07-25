package com.booking.yoya.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.booking.yoya.model.common.SharedEntity;

import lombok.Data;

@Entity
@Table(name = "ticket_support_reply")
@Data
//@Where(clause = "is_active = true")
public class TicketSupportReply extends SharedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_chat_id")
	private Long ticketChatId;

	@Column(name = "ticket_id", nullable = false)
	private Long ticketId;

	@Column(name = "ticket_type", nullable = false)
	private String ticketType;

	@Column(name = "message", nullable = false)
	private String message;

}
