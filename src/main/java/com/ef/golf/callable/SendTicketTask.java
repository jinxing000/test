package com.ef.golf.callable;

import com.ef.golf.pojo.SmallRedPackage;
import com.ef.golf.service.RedPackageService;
import com.ef.golf.service.UserService;
import com.ef.golf.service.UserTicketService;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.util.RedisExpireTime;
import com.ef.golf.vo.MineVo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2018/5/10.
 */
@Component
@Scope("prototype")
public class SendTicketTask implements Callable<Map<String,Object>>, Serializable {

    @Resource
    RedisBaseDao redisBaseDao;
    @Resource
    private UserTicketService userTicketService;
    @Resource
    private UserService userService;
    //发送者id
    private String userId;

    //接受者id
    private  String recieverId;

    //优惠券id
    private String youHuiQuanId;

    //(String)map.get("ticketZzId")
//抢券记录id
    private String ticketZzId;

    //发放类型
    private String sendType;//  1.个人到个人  2.群里发放
    //status="0" 成功  status="1" 失败 status="2" 已发放
    @Override
    public Map<String, Object> call() throws Exception {
        System.out.println("task===sendType====================="+sendType);
        Map<String,Object> map=new HashMap<>();
            // 首先判断该券是否已被所属者发放且未失效
       // if(redisBaseDao.exist("byQianQuan")){

       /* if(redisBaseDao.exist(ticketZzId)){
            Map<String,Object> checkMap = redisBaseDao.getMap(ticketZzId);
            String redisStatus =(String)checkMap.get("status");
            String redisYouHuiQuanId = (String) checkMap.get("youHuiQuanId");
            String redisUserId = (String) checkMap.get("userId");
            if(userId.equals(redisUserId)&&youHuiQuanId.equals(redisYouHuiQuanId)){
                Map<String,Object>resultMap = new HashMap<>();
                resultMap.put("status","2");
                if(redisStatus.equals("3")){
                    resultMap.put("message","券已被抢了");
                }else {
                    resultMap.put("message", "该券已发过");
                }
                return resultMap;
            }
        }*/
        //把优惠券存入redis 设置过期时间
        Map<String,Object> redisMap = new HashMap<>();
        try{
            //判断优惠券发放类型   1代表 个人到个人  2.群发
            if ("1".equals(sendType)){//个人到个人发放  存入redis设置过期时间
                redisBaseDao.saveEx("youHuiQuan",youHuiQuanId,259200);
                //调用优惠券转赠接口，更新优惠券所属  同时优惠券的流转记录  优惠券发放表 谁 什么时候 发的哪个券
                Map<String,Object> resultMap = userTicketService.zhuanZeng(youHuiQuanId,userId,recieverId,sendType);

                redisMap.put("status","1");
                map.put("status",resultMap.get("code"));
                map.put("message","success");
                map.put(resultMap.get("ticketZzId")+"",resultMap);
            }else{//群发 优惠券领取状态 0

                redisMap.put("status","0");

                //发券的时候写入数据库
                map =  userTicketService.qianYouhuiQuan(youHuiQuanId,userId,"0");//发的时候不知道接收用户是谁先给0
              /*  redisBaseDao.setMap("byQianQuan",redisMap);
                redisBaseDao.expire("byQianQuan",86400);*/
              //  Map map11 = redisBaseDao.getMap("byQianQuan");
                map.put("status","0");
                map.put("message","success");
            }
            redisMap.put("userId",userId);
            redisMap.put("youHuiQuanId",youHuiQuanId);
            redisBaseDao.setMap(map.get("ticketZzId")+"",redisMap);
            redisBaseDao.expire(map.get("ticketZzId")+"", RedisExpireTime.CouponTime.getSeconds());
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getTicketZzId() {
        return ticketZzId;
    }

    public void setTicketZzId(String ticketZzId) {
        this.ticketZzId = ticketZzId;
    }
}
