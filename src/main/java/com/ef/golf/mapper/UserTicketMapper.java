package com.ef.golf.mapper;

import com.ef.golf.pojo.UserTicket;

import java.util.List;
import java.util.Map;

public interface UserTicketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTicket record);

    int insertSelective(UserTicket record);

    UserTicket selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTicket record);

    int updateByPrimaryKey(UserTicket record);

    int updateUserTicketById(Map map);

    int isExpire(Map<String, String> map);

    int youHuiQuanZhuangZen(Map map);
    int insertUserTickets(List<UserTicket>list);
}