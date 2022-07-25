package com.booking.yoya.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.yoya.dao.TicketSupportDao;
import com.booking.yoya.dto.TicketSupportDto;
import com.booking.yoya.model.TicketSupport;
import com.booking.yoya.response.BaseMessageResponse;

@Service
public class TicketSupportService {

	private static Logger logger = LogManager.getLogger();

	@Autowired
	private TicketSupportDao ticketSupportDao;

	public BaseMessageResponse<Object> createNewTicketSupport(TicketSupportDto ticketSupportDto) {

		try {
			System.out.println(ticketSupportDto.toString());
			TicketSupport ticketSupport = new TicketSupport();
			ticketSupport.setDriverId(ticketSupportDto.getDriverId());
			ticketSupport.setSubject(ticketSupportDto.getSubject());
			ticketSupport.setTicketStatus(ticketSupportDto.getTicketStatus());
			ticketSupport.setTicketType(ticketSupportDto.getTicketType());
			ticketSupport.setUserId(ticketSupportDto.getUserId());

			ticketSupport.setUniqueId(UUID.randomUUID().toString());

			TicketSupport savedTicketSupport = ticketSupportDao.save(ticketSupport);
			return BaseMessageResponse.builder().message("success").status(true).data(savedTicketSupport).build();

		} catch (Exception e) {
			e.printStackTrace();
			return BaseMessageResponse.builder().message("Something went wrong").status(false).data(e.getMessage())
					.build();

		}

	}

	public BaseMessageResponse<Object> getAllTicketSupports() {
		try {

			logger.info("ticketSupportDao list " + ticketSupportDao.findAll());

			List<TicketSupport> ticketSupportList = ticketSupportDao.findAll();
			if (ticketSupportList.isEmpty()) {
				return BaseMessageResponse.builder().message("success").status(false).data(ticketSupportList).build();
			} else {
				List<TicketSupportDto> ticketSupportDtoList = ticketSupportList.stream()
						.map(new Function<TicketSupport, TicketSupportDto>() {
							@Override
							public TicketSupportDto apply(TicketSupport t) {

								TicketSupportDto tDto = new TicketSupportDto();

								tDto.setTicketId(t.getTicketId());
								tDto.setDriverId(t.getDriverId());
								tDto.setSubject(t.getSubject());
								tDto.setTicketStatus(t.getTicketStatus());
								tDto.setTicketType(t.getTicketType());
								tDto.setUniqueId(t.getUniqueId());
								tDto.setUserId(t.getUserId());

								return tDto;
							}
						}).collect(Collectors.toList());

				logger.info("TicketSupport list to be send " + ticketSupportDtoList);

				return BaseMessageResponse.builder().message("success").status(true).data(ticketSupportDtoList).build();

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("exception " + e);
			return BaseMessageResponse.builder().message("Something weny wrong").status(false).data(e.getMessage())
					.build();
		}
	}

	public BaseMessageResponse<Object> deleteTicketSupport(Long ticketId) {
		try {
			Optional<TicketSupport> ticketSupport = ticketSupportDao.findById(ticketId);

			if (!ticketSupport.isPresent()) {
				return BaseMessageResponse.builder().message("TicketSupport not present").status(false)
						.data(ticketSupport).build();

			} else {
				ticketSupportDao.deleteById(ticketSupport.get().getTicketId());
				return BaseMessageResponse.builder().message("success").status(true).data(ticketSupport).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return BaseMessageResponse.builder().message("Something went wrong").status(false).data(e.getMessage())
					.build();
		}

	}

	public BaseMessageResponse<Object> ticketSupportById(Long ticketSupportId) {
		try {
			Optional<TicketSupport> ticketSupport = ticketSupportDao.findById(ticketSupportId);

			if (!ticketSupport.isPresent()) {
				return BaseMessageResponse.builder().message("TicketSupport not present").status(false)
						.data(ticketSupport).build();

			} else {
				TicketSupportDto tDto = new TicketSupportDto();

				tDto.setTicketId(ticketSupport.get().getTicketId());
				tDto.setDriverId(ticketSupport.get().getDriverId());
				tDto.setSubject(ticketSupport.get().getSubject());
				tDto.setTicketStatus(ticketSupport.get().getTicketStatus());
				tDto.setTicketType(ticketSupport.get().getTicketType());
				tDto.setUniqueId(ticketSupport.get().getUniqueId());
				tDto.setUserId(ticketSupport.get().getUserId());
				return BaseMessageResponse.builder().message("success").status(true).data(tDto).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return BaseMessageResponse.builder().message("Something went wrong").status(false).data(e.getMessage())
					.build();
		}

	}

}
