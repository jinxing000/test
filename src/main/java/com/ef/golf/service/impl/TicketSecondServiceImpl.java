package com.ef.golf.service.impl;


import com.ef.golf.mapper.TicketsMapper;
import com.ef.golf.service.TicketSecondService;
import com.ef.golf.vo.TicketSeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TicketSecondServiceImpl implements TicketSecondService {
    @Autowired
    TicketsMapper ticketMapper;
    @Override
    public List<TicketSeVo> getTicketList(Integer user_id) {
        return ticketMapper.getTicketList(user_id);
    }
}
