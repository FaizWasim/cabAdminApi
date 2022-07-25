package com.booking.yoya.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.yoya.dto.CityDto;
import com.booking.yoya.dto.TicketSupportDto;
import com.booking.yoya.model.City;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.CityService;
import com.booking.yoya.service.TicketSupportService;

@RestController
@RequestMapping("api/v1/ticketSupport")
public class TicketSupportController {
	private static Logger logger = LogManager.getLogger();
	@Autowired
	private TicketSupportService ticketSupportService;

	@PostMapping(value = "create")
	public ServiceResponse<?> createSupportTicket(@RequestBody TicketSupportDto ticketSupportDto) {
		return new ServiceResponse<BaseMessageResponse>(ticketSupportService.createNewTicketSupport(ticketSupportDto));
	}

	@GetMapping(value = "list")
//	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> ticketSupportList() {
		return new ServiceResponse<BaseMessageResponse>(ticketSupportService.getAllTicketSupports());
	}

	@DeleteMapping(value = "{ticketSupportId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> deleteTicketSupport(@PathVariable Long ticketSupportId) {
		return new ServiceResponse<BaseMessageResponse>(ticketSupportService.deleteTicketSupport(ticketSupportId));
	}

	@GetMapping(value = "{ticketSupportId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> ticketSupportById(@PathVariable Long ticketSupportId) {
		return new ServiceResponse<BaseMessageResponse>(ticketSupportService.ticketSupportById(ticketSupportId));
	}

}
