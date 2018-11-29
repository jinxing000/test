package com.ef.golf.common.pxx.controller;


import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.exception.LoginException;
import com.ef.golf.service.UserService;
import com.ef.golf.util.HttpGetIpUtil;
import com.ef.golf.util.OrderNoUtil;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Order;
import com.pingplusplus.model.Recharge;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * create by xzw
 * 2018年1月23日13:35:16
 * 用户创建订单
 */
@Controller
@RequestMapping("/create")
public class CreateOrderController {

    @Resource
    private PingUtil pingUtil;
    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;
    @Resource
    private UserService userService;
    @Resource
    private OrderNoUtil orderNoUtil;

    //创建订单
    @RequestMapping("/createOrder")
    @ResponseBody
    public String createOrder(String uid,String sid, String orderNo, String orderType, String phoneNumber, String subject, String body, int amount, HttpServletRequest request) throws LoginException {
        String userId=redisLoginVerifyUtil.longinVerifty(sid, uid).toString();
        String ip=getIpAddress(request);
        int payMoney=amount*100;
        /*Order obj=pingUtil.createOrder(userId,orderNo,orderType,phoneNumber,subject,body,payMoney,ip);*/
        return null;
    }

    //支付订单
    @RequestMapping("/orderPay")
    @ResponseBody
    public String orderPay(String channel,int amount,String orderNo){
       Order obj= pingUtil.pay(channel,amount,orderNo);
        return obj.toString();
    }



    /**
     * //根据用户id查询订单列表
     * @param uid
     * @param sid
     * @param isPaid 是否已付款
     * @param isRefunded 是否存在退款信息，无论退款是否成功
     * @return
     */
    @RequestMapping("/orderList")
    @ResponseBody
    public String orderList(String uid,String sid,boolean isPaid,boolean isRefunded) throws LoginException {
        String userId=redisLoginVerifyUtil.longinVerifty(sid, uid).toString();
        String obj=pingUtil.orderList(userId,isPaid,isRefunded);
        return obj;
    }


    /**
     * 充值
     * @param uid
     * @param sid
     * @param description
     * @param amount
     * @param channel
     * @param subject
     * @param body
     * @param request
     * @return
     */
    @RequestMapping("/recharge")
    @ResponseBody
    public String recharge(String uid,String sid,String description,int amount,String channel,String subject,String body,HttpServletRequest request) throws LoginException {
        String userId=redisLoginVerifyUtil.longinVerifty(uid, sid).toString();
        String userPhone=userService.getInfo(Integer.parseInt(userId)).getPhone();
        int money=amount*100;
        String clientIp= HttpGetIpUtil.getIpAddress(request);
        String orderNo=pingUtil.generateOrderNo("r",userPhone);
        //充值
        Recharge obj=pingUtil.recharge(userId,description,money,channel,orderNo,clientIp,subject,body);
        //赠送
        String presentNo= orderNoUtil.serialNoGenerate("05",userPhone);
        //pingUtil.presentMoney(userId,money,presentNo,presentDescription);
        return obj.toString();
    }




    //获取客户端ip
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


}
