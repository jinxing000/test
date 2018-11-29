package com.ef.golf.controller;

import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.exception.LoginException;
import com.ef.golf.pojo.*;
import com.ef.golf.service.*;
import com.ef.golf.util.AmountUtils;
import com.ef.golf.util.HttpGetIpUtil;
import com.ef.golf.util.OrderNoUtil;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.pingplusplus.model.Recharge;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
  充值
 */
@Controller
public class RechargeController {

    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;
    @Resource
    private PingUtil pingUtil;
    @Resource
    private OrderNoUtil orderNoUtil;

    @ResponseBody
    @RequestMapping(value = "/recharge",method = RequestMethod.POST)
    public Object recharge(String uid,String sid,String description,int amount,String channel,String subject,String body,HttpServletRequest request){
        /**
         *充值
         * @param userId 用户id
         * @param description 描述
         * @param amount 充值金额（单位：分）
         * @param channel 支付方式/渠道
         * @param orderNo 订单编号
         * @param clientIp 客户端ip
         * @param subject 充值标题
         * @param body 充值描述
         * @return
         */
        Recharge recharge = null;
        try {
            Integer userId =  redisLoginVerifyUtil.longinVerifty(sid,uid);
                String clientIp= HttpGetIpUtil.getIpAddress(request);
                String orderNo = orderNoUtil.orderNoGenerate("01",uid);
                subject = "余额充值";
                body = "-9999";//198641
                description = "1";
                    System.out.println("================"+orderNo);
                recharge = pingUtil.recharge(userId.toString(),description,amount,channel,orderNo,clientIp,subject,body);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return IfunResult.ok(recharge);
    }
}
