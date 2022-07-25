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
@Table(name = "ticket_support")
@Data
//@Where(clause = "is_active = true")
public class TicketSupport extends SharedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_id")
	private Long ticketId;

	@Column(name = "unique_id", nullable = false)
	private String uniqueId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "driver_id")
	private Long driverId;

	@Column(name = "subject", nullable = false)
	private String subject;

	@Column(name = "ticket_type", nullable = false)
	private String ticketType;

	@Column(name = "ticket_status", nullable = false)
	private String ticketStatus;

}
