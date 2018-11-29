package com.ef.golf.mapper;

import com.ef.golf.pojo.Ticket;
import com.ef.golf.pojo.Tickets;
import com.ef.golf.vo.TicketSeVo;

import java.util.List;

public interface TicketsMapper {
    int deleteByPrimaryKey(Integer ticketId);

    int insert(Tickets record);

    int insertSelective(Tickets record);

    Tickets selectByPrimaryKey(Integer ticketId);

    int updateByPrimaryKeySelective(Tickets record);

    int updateByPrimaryKey(Tickets record);
    List<TicketSeVo>getTicketList(Integer user_id);
    TicketSeVo getTicketListDetails(String youHuiQuanId);
    List<Tickets> getTicketListIsShopSendTicket(List<String>list);
}