package com.ef.golf.service;


import com.ef.golf.pojo.TicketSend;
import com.ef.golf.pojo.UserTicket;
import com.ef.golf.vo.ByQianTicketDetailsVo;

import java.util.Map;

/**
 * com.ef.golf.service
 * Administrator
 * 2018/5/4 10:46
 * 发放记录表
 */
public interface UserTicketService {

    int updateUserTicketById(Integer userId,Integer ticketId);

    //判断券是否过期
    int isExpire(Map<String,String> map);
    //优惠券转赠接口
    Map<String,Object> zhuanZeng(String youHuiQuanId, String senderId, String recieverId,String sendType);

    Map<String,Object> qianYouhuiQuan(String youHuiQuanId, String senderId, String recieverId);

    Map<String,Object> qianYouhuiQuan2(String youHuiQuanId, String senderId, String recieverId);//获取券和人的信息

    Map<String,Object> selectByQianTicketDetails(String youHuiQuanId, String userId,String ticketZzId);

    UserTicket selectByPrimaryKey(String youHuiQuanId);

    int updateTicketSendByTicketZzId(TicketSend ticketSend);
}
