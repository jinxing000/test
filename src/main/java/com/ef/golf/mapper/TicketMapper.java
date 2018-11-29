package com.ef.golf.mapper;

import com.ef.golf.pojo.Ticket;
import com.ef.golf.pojo.User;
import com.ef.golf.vo.TicketVo;

import java.util.List;

public interface TicketMapper {
    int deleteByPrimaryKey(Integer ticketId);

    int insert(Ticket record);

    int insertSelective(Ticket record);

    Ticket selectByPrimaryKey(Integer ticketId);

    int updateByPrimaryKeySelective(Ticket record);

    int updateByPrimaryKey(Ticket record);

    //获取用户优惠券
    List<TicketVo> getUserTickets(User user);


}