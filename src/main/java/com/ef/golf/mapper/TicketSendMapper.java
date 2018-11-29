package com.ef.golf.mapper;

import com.ef.golf.pojo.TicketSend;
import com.ef.golf.vo.ByQianTicketDetailsVo;

import java.util.List;
import java.util.Map;

public interface TicketSendMapper {
    int deleteByPrimaryKey(Long ticketZzId);

    int insert(TicketSend record);

    int insertSelective(TicketSend record);

    TicketSend selectByPrimaryKey(Long ticketZzId);

    int updateByPrimaryKeySelective(TicketSend record);

    int updateByPrimaryKey(TicketSend record);

    ByQianTicketDetailsVo selectByQianTicketDetails(String ticketZzId);
}