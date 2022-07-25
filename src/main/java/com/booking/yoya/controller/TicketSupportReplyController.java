package com.booking.yoya.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.yoya.dto.TicketSupportReplyDto;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.TicketSupportReplyService;

@RestController
@RequestMapping("api/v1/ticketSupportReply")
public class TicketSupportReplyController {
	private static Logger logger = LogManager.getLogger();
	@Autowired
	private TicketSupportReplyService ticketSupportServiceReplyService;

	@PostMapping(value = "create")
	public ServiceResponse<?> createTicketSupportReply(@RequestBody TicketSupportReplyDto ticketSupportReplyDto) {
		return new ServiceResponse<BaseMessageResponse>(
				ticketSupportServiceReplyService.createNewTicketSupportReply(ticketSupportReplyDto));
	}

	@GetMapping(value = "list/{ticketId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> ticketSupportReplyByTicketId(@PathVariable Long ticketId) {
		return new ServiceResponse<BaseMessageResponse>(
				ticketSupportServiceReplyService.getAllTicketByTicketId(ticketId));
	}

}
