package com.ef.golf.service.impl;

import com.ef.golf.mapper.TicketSendMapper;
import com.ef.golf.mapper.TicketsMapper;
import com.ef.golf.mapper.UserMapper;
import com.ef.golf.mapper.UserTicketMapper;
import com.ef.golf.pojo.Ticket;
import com.ef.golf.pojo.TicketSend;
import com.ef.golf.pojo.Tickets;
import com.ef.golf.pojo.UserTicket;
import com.ef.golf.service.TicketService;
import com.ef.golf.service.UserService;
import com.ef.golf.service.UserTicketService;
import com.ef.golf.vo.ByQianTicketDetailsVo;
import com.ef.golf.vo.MineVo;
import com.ef.golf.vo.TicketSeVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/5/4 10:47
 */
@Repository
public class UserTicketServiceImpl implements UserTicketService {

    @Resource
    private UserTicketMapper userTicketMapper;
    @Resource
    private TicketSendMapper ticketSendMapper;
    @Resource
    private TicketsMapper ticketsMapper;
    @Resource
    private UserMapper userMapper;
    @Override
    public int updateUserTicketById(Integer userId, Integer ticketId) {
        Map<String,Object>map = new HashMap<>();
        map.put("userId",userId);
        map.put("ticketId",ticketId);
        return userTicketMapper.updateUserTicketById(map);
    }

    @Override
    public int isExpire(Map<String, String> map) {
        return userTicketMapper.isExpire(map);
    }

    @Override
    public Map<String,Object> zhuanZeng(String youHuiQuanId, String senderId, String recieverId,String sendType) {
        Map<String,Object>resultMap = new HashMap<>();
        Date date = new Date();
        /** 优惠券转赠 */
        Map<String,Object> map = new HashMap<>();
        map.put("userId",senderId);
        UserTicket userTicket = userTicketMapper.selectByPrimaryKey(Integer.valueOf(youHuiQuanId));
        map.put("ticketId",youHuiQuanId);
        map.put("recieverId",recieverId);
        int i = userTicketMapper.youHuiQuanZhuangZen(map);
        /** 转赠记录 */
        if(i>0){
            int ticketZzId=insertTicketSend(userTicket.getTicketId()+"",senderId,recieverId,date);
            resultMap.put("code",0);
            resultMap.put("message","YES");
            resultMap.put("ticketZzId",ticketZzId);
        }else{
            resultMap.put("code",1);
            resultMap.put("message","NO");
        }
        return resultMap;
    }
    @Override
    public Map<String,Object> qianYouhuiQuan(String youHuiQuanId, String senderId, String recieverId) {
        Map<String,Object>resultMap = new HashMap<>();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        UserTicket userTicket = userTicketMapper.selectByPrimaryKey(Integer.valueOf(youHuiQuanId));
        TicketSeVo tickets = ticketsMapper.getTicketListDetails(userTicket.getTicketId()+"");//优惠券详情

        MineVo mineVo = userMapper.getInfo(Integer.valueOf(recieverId));//用户详情
        /** 优惠券转赠 */
        if(senderId.equals(recieverId)){//本人抢
            int ticketZzId = insertTicketSend(youHuiQuanId,senderId,recieverId,date);//转赠记录

            resultMap.put("tickets",tickets);
            resultMap.put("mineVo",mineVo);
            resultMap.put("time",sdf.format(date));
            resultMap.put("status","0");
            resultMap.put("ticketZzId",ticketZzId);
        }else{
            Map<String,Object> map = new HashMap<>();
            map.put("userId",senderId);
            map.put("ticketId",youHuiQuanId);
            map.put("recieverId",recieverId);
           userTicketMapper.youHuiQuanZhuangZen(map);//更改用户群所属

            int ticketZzId =  insertTicketSend(youHuiQuanId,senderId,recieverId,date);//转赠记录
            resultMap.put("tickets",tickets);
            resultMap.put("mineVo",mineVo);
            resultMap.put("time",sdf.format(date));
            resultMap.put("status","1");
            resultMap.put("ticketZzId",ticketZzId);
        }
        return resultMap;


    }

    @Override
    public Map<String, Object> qianYouhuiQuan2(String youHuiQuanId, String senderId, String recieverId) {

        System.out.println("qianYouhuiQuan2===="+recieverId);

        Map<String,Object>resultMap = new HashMap<>();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UserTicket userTicket = userTicketMapper.selectByPrimaryKey(Integer.valueOf(youHuiQuanId));
        TicketSeVo tickets = ticketsMapper.getTicketListDetails(youHuiQuanId);//优惠券详情
        MineVo mineVo = userMapper.getInfo(Integer.valueOf(recieverId));//用户详情
        /** 优惠券转赠 */

            Map<String,Object> map = new HashMap<>();
           // map.put("userId",senderId);
            map.put("ticketId",youHuiQuanId);
            map.put("recieverId",recieverId);
            userTicketMapper.youHuiQuanZhuangZen(map);//更改用户群所属


            resultMap.put("tickets",tickets);
            resultMap.put("mineVo",mineVo);
            resultMap.put("time",sdf.format(date));
            resultMap.put("status","1");


        return resultMap;
    }

    @Override
    public Map<String,Object> selectByQianTicketDetails(String youHuiQuanId, String userId,String ticketZzId) {

        System.out.println("youHuiQuanId==="+youHuiQuanId+"====userId"+userId);
        Map<String,Object>resultMap = new HashMap<>();
        if(!"".equals(youHuiQuanId)&&null!=youHuiQuanId&&!"".equals(userId)&&null!=userId){
        UserTicket userTicket = userTicketMapper.selectByPrimaryKey(Integer.valueOf(youHuiQuanId));
        TicketSeVo tickets = ticketsMapper.getTicketListDetails(youHuiQuanId);//优惠券详情
      /*  Map<String,Object>map = new HashMap<>();
        map.put("userId",userId);
        map.put("ticketId",youHuiQuanId);*/

      ByQianTicketDetailsVo byQianTicketDetailsVo = ticketSendMapper.selectByQianTicketDetails(ticketZzId);
        resultMap.put("tickets",tickets);
        resultMap.put("byQianTicketDetailsVo",byQianTicketDetailsVo);
        }
        return resultMap;
    }

    @Override
    public UserTicket selectByPrimaryKey(String youHuiQuanId) {
        return userTicketMapper.selectByPrimaryKey(Integer.valueOf(youHuiQuanId));
    }

    @Override
    public int updateTicketSendByTicketZzId(TicketSend ticketSend) {

        return ticketSendMapper.updateByPrimaryKeySelective(ticketSend);
    }

    protected int insertTicketSend(String youHuiQuanId, String senderId, String recieverId,Date date){
        TicketSend ticketSend = new TicketSend();
        ticketSend.setRecieverId(Integer.valueOf(recieverId));
        ticketSend.setCreateTime(date);
        ticketSend.setSendType("2");
        UserTicket userTicket = userTicketMapper.selectByPrimaryKey(Integer.valueOf(youHuiQuanId));
        ticketSend.setTicketId(Integer.valueOf(youHuiQuanId));
        ticketSend.setUserId(Integer.valueOf(senderId));
        int i = ticketSendMapper.insertSelective(ticketSend);
        return ticketSend.getTicketZzId();
    }
}
