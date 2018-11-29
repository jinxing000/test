package com.ef.golf.service;

import com.ef.golf.pojo.Ticket;
import com.ef.golf.pojo.Tickets;
import com.ef.golf.pojo.User;
import com.ef.golf.vo.TicketVo;

import java.util.List;

/**
 * for efgolf
 * Created by Bart on 2017/9/25.
 * Date: 2017/9/25 11:12
 */
public interface TicketService {

    Ticket getTicket(int id);

    //获取用户优惠券
    List<TicketVo> getUserTickets(User user);
    //查询优惠券
    Tickets selectByPrimaryKey(Integer ticketId);
}
