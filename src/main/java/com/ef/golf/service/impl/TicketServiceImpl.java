package com.ef.golf.service.impl;

import com.ef.golf.mapper.TicketMapper;
import com.ef.golf.mapper.TicketsMapper;
import com.ef.golf.pojo.Ticket;
import com.ef.golf.pojo.Tickets;
import com.ef.golf.pojo.User;
import com.ef.golf.service.TicketService;
import com.ef.golf.vo.TicketVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * for efgolf
 * Created by Bart on 2017/9/25.
 * Date: 2017/9/25 11:13
 */

@Repository
public class TicketServiceImpl implements TicketService {


    @Resource
    private TicketMapper ticketMapper;

    @Resource
    private TicketsMapper ticketsMapper;



    public Ticket getTicket(int id) {
        return ticketMapper.selectByPrimaryKey(id);
    }

    public List<TicketVo> getUserTickets(User user) {
        return ticketMapper.getUserTickets(user);
    }

    @Override
    public Tickets selectByPrimaryKey(Integer ticketId) {
        return ticketsMapper.selectByPrimaryKey(ticketId);
    }
}
