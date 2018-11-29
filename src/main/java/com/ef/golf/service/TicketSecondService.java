package com.ef.golf.service;

import com.ef.golf.vo.TicketSeVo;

import java.util.List;

public interface TicketSecondService {

    public List<TicketSeVo>getTicketList(Integer user_id);
}
