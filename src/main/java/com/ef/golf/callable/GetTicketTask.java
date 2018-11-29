package com.ef.golf.callable;

import com.ef.golf.mapper.UserTicketMapper;
import com.ef.golf.pojo.TicketSend;
import com.ef.golf.service.UserService;
import com.ef.golf.service.UserTicketService;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.util.ServicePushUtils;
import com.ef.golf.vo.MineVo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2018/5/10.
 */
@Component
@Scope("prototype")
public class GetTicketTask implements Callable<Map<String,Object>>, Serializable {

    @Resource
    RedisBaseDao redisBaseDao;
    @Resource
    private UserTicketService userTicketService;
    //接受者id
    private  String recieverId;

    //优惠券id
    private String youHuiQuanId;

    //抢券记录id
    private String ticketZzId;
    @Override
    public Map<String, Object> call() throws Exception {

        System.out.println("recieverId=========="+recieverId+"====youHuiQuanId======"+youHuiQuanId);
        Map<String,Object> map=new HashMap<>();
        Map<String,Object>resultMap=null;
        //status="0" 本人抢 status="1" 非本人抢 status="2" 已过时 status="3" 已被抢
        try{//
            /** 判断优惠券是否存在 */
           // if(!redisBaseDao.exist("byQianQuan")){

            if(!redisBaseDao.exist(ticketZzId)){
                map.put("status",2);
                map.put("message","已过时");
            }else{
               // Map<String,Object> redisMap = redisBaseDao.getMap("byQianQuan");
                Map<String,Object> redisMap = redisBaseDao.getMap(ticketZzId);
                System.out.println("redisMap======="+redisMap);
                String status =(String)redisMap.get("status");
                String youHuiQuanId = (String) redisMap.get("youHuiQuanId");
                String userId = (String) redisMap.get("userId");
                /** 优惠券存在未被抢 */
                if("0".equals(status)){
                    System.out.println("========未被抢=======");
                    System.out.println("youHuiQuanId======="+youHuiQuanId+"=====userId====="+userId+"=====recieverId==="+recieverId);
                  //获取TicketSend对象
                    TicketSend ticketSend=new TicketSend();
                    ticketSend.setTicketZzId(Integer.valueOf(ticketZzId));
                    ticketSend.setRecieverId(Integer.valueOf(recieverId));

                    userTicketService.updateTicketSendByTicketZzId(ticketSend);//填充接受者

                   resultMap =  userTicketService.qianYouhuiQuan2(youHuiQuanId,userId,recieverId);

                    Map<String,Object> upRedisMap = new HashMap<>();
                    upRedisMap.put("status","1");
                    upRedisMap.put("userId",userId);
                    upRedisMap.put("youHuiQuanId",youHuiQuanId);
                    redisBaseDao.setMap(ticketZzId,upRedisMap);
                    redisBaseDao.expire(ticketZzId,86400);
                   // redisBaseDao.getMap()
                    map.put("status",resultMap.get("status"));
                    map.put("tickets",resultMap.get("tickets"));
                    map.put("mineVo",resultMap.get("mineVo"));
                    map.put("time",resultMap.get("time"));
                }else{
                    System.out.println("========被抢了=======");

                    Map<String,Object>byRedisMap = redisBaseDao.getMap(ticketZzId);
                    System.out.println("youHuiQuanId======="+(String) byRedisMap.get("youHuiQuanId")+"=====userId====="+(String) byRedisMap.get("userId")+"=====recieverId==="+recieverId);
                    Map<String,Object> resultMap2 = userTicketService.selectByQianTicketDetails((String) byRedisMap.get("youHuiQuanId"),(String) byRedisMap.get("userId"),ticketZzId);
                    map.put("status",3);
                    map.put("tickets",resultMap2.get("tickets"));
                    map.put("byQianTicketDetailsVo",resultMap2.get("byQianTicketDetailsVo"));
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    public String getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(String recieverId) {
        this.recieverId = recieverId;
    }

    public String getYouHuiQuanId() {
        return youHuiQuanId;
    }

    public void setYouHuiQuanId(String youHuiQuanId) {
        this.youHuiQuanId = youHuiQuanId;
    }

    public void setTicketZzId(String ticketZzId) {
        this.ticketZzId = ticketZzId;
    }
}
