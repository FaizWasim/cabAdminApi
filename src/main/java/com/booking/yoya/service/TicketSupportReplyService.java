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
import com.booking.yoya.dao.TicketSupportReplyDao;
import com.booking.yoya.dto.TicketSupportDto;
import com.booking.yoya.dto.TicketSupportReplyDto;
import com.booking.yoya.model.TicketSupport;
import com.booking.yoya.model.TicketSupportReply;
import com.booking.yoya.response.BaseMessageResponse;

@Service
public class TicketSupportReplyService {

	private static Logger logger = LogManager.getLogger();

	@Autowired
	private TicketSupportReplyDao ticketSupportReplyDao;

	public BaseMessageResponse<Object> createNewTicketSupportReply(TicketSupportReplyDto ticketSupportReplyDto) {

		try {

			System.out.println(ticketSupportReplyDto.toString());
			TicketSupportReply ticketSupportReply = new TicketSupportReply();

			ticketSupportReply.setTicketId(ticketSupportReplyDto.getTicketId());
			ticketSupportReply.setMessage(ticketSupportReplyDto.getMessage());
			ticketSupportReply.setTicketType(ticketSupportReplyDto.getTicketType());

			TicketSupportReply savedTicketSupportReply = ticketSupportReplyDao.save(ticketSupportReply);
			return BaseMessageResponse.builder().message("success").status(true).data(savedTicketSupportReply).build();

		} catch (Exception e) {
			e.printStackTrace();
			return BaseMessageResponse.builder().message("Something went wrong").status(false).data(e.getMessage())
					.build();

		}

	}

	public BaseMessageResponse<Object> getAllTicketByTicketId(Long ticketId) {
		try {

			logger.info("ticketSupportDao list " + ticketSupportReplyDao.findByTicketId(ticketId));

			List<TicketSupportReply> ticketSupportReplyList = ticketSupportReplyDao.findByTicketId(ticketId);
			if (ticketSupportReplyList.isEmpty()) {
				return BaseMessageResponse.builder().message("success").status(false).data(ticketSupportReplyList)
						.build();
			} else {
				List<TicketSupportReplyDto> ticketSupportReplyDtoList = ticketSupportReplyList.stream()
						.map(new Function<TicketSupportReply, TicketSupportReplyDto>() {
							@Override
							public TicketSupportReplyDto apply(TicketSupportReply t) {

								TicketSupportReplyDto tDto = new TicketSupportReplyDto();

								tDto.setTicketChatId(t.getTicketChatId());
								tDto.setTicketId(t.getTicketId());
								tDto.setMessage(t.getMessage());
								tDto.setTicketType(t.getTicketType());

								return tDto;
							}
						}).collect(Collectors.toList());

				logger.info("TicketSupportReply list to be send " + ticketSupportReplyDtoList);

				return BaseMessageResponse.builder().message("success").status(true).data(ticketSupportReplyDtoList)
						.build();

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("exception " + e);
			return BaseMessageResponse.builder().message("Something weny wrong").status(false).data(e.getMessage())
					.build();
		}
	}

}
