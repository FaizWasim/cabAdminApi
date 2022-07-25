package com.booking.yoya.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.TicketSupportReply;

@Repository
public interface TicketSupportReplyDao extends JpaRepository<TicketSupportReply, Long> {

	public static final String FINDBYTICKETID = "SELECT * from ticket_support_reply where ticket_id=:ticketId";

	@Query(value = FINDBYTICKETID, nativeQuery = true)
	List<TicketSupportReply> findByTicketId(Long ticketId);

}
