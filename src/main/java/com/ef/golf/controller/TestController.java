package com.ef.golf.controller;

import com.alibaba.fastjson.JSONObject;
import com.ef.golf.aspectj.Syslog;
import com.ef.golf.common.Constant;
import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.exception.SystemException;
import com.ef.golf.util.HttpUtil;
import com.ef.golf.util.OrderNoUtil;
import com.ef.golf.util.RedisBaseDao;
import com.pingplusplus.Pingpp;
import com.pingplusplus.model.BalanceTransfer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * for efgolf
 * Created by Bart on 2017/1/6.
 * Date: 2017/1/6 09:16
 */
@Controller
/*@RequestMapping(value = "/push")*/
public class TestController {

    @Resource
    private PingUtil pingUtil;
    @Resource
    private OrderNoUtil orderNoUtil;

    @Resource
    private RedisBaseDao redisBaseDao;

   /* @Value("${cancelTitle}")
    private String title;*/

    @Value("${coachCloseMoney}")
   private String coachCloseMoney;

    @ResponseBody
    @RequestMapping(value = "/ssss",method = RequestMethod.POST)
    public Object Testtest(HttpServletResponse response){
        Map<String,Object>map = new HashMap<>();
        List<String>list1 = new ArrayList<>();
        list1.add("1");
        map.put("1",list1);
        redisBaseDao.setMap("sss",map,5000);

        Map<String,Object> caddieRewardMap = redisBaseDao.getMap("sss");
        List<String>list = (List<String>) caddieRewardMap.get("1");

        if(list.size()>0){
            System.out.println(1111);
        }

       /* Cookie cookie = new Cookie("userName","sss");
        cookie.setPath("/");
        cookie.setMaxAge(10);
        response.addCookie(cookie);

        SmsUtil.sendSMS("15620606384",coachCloseMoney,new String[]{"600000000000000000"});*/
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public Object TestJms(HttpServletRequest request,String userId){
        com.pingplusplus.model.User user2 = pingUtil.createUser(userId);
     /* String orderNo = orderNoUtil.orderNoGenerate("r","1111111111");
       String clientIp= HttpGetIpUtil.getIpAddress(request);
       Recharge recharge = pingUtil.recharge(userId,"系统账户充值",100000000,"custom",orderNo,clientIp,"啦啦","充值");*/
        return  user2;
    }

    @ResponseBody
    @RequestMapping(value = "/userBalance",method = RequestMethod.POST)
    public Object fingUserBalance(HttpServletRequest request) throws SystemException {
        //User user = pingUtil.userBalacnce("298");
        try{
            throw new SystemException(Constant.ERR_PRICE);
        }catch (Exception e){
            throw new SystemException(Constant.ERR_PRICE);
        }finally {
            throw new SystemException(Constant.ERR_PRICE);
        }

        //return  user.getAvailableBalance();
    }
    @ResponseBody
    @RequestMapping(value = "/transfer",method = RequestMethod.POST)
    public Object transfer(String userId) {
        String orderNo = orderNoUtil.serialNoGenerate("06", "15620532538");
        /*Transfer transfer = pingUtil.transferAccounts(500000, "balance", orderNo, "47", "给徐强发钱");*/
        System.out.println("userId  "+userId);
        System.out.println("orderNo" + orderNo);
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("java.vendor.url"));
        System.out.println("Pingpp.VERSION"+Pingpp.VERSION);
        BalanceTransfer balanceTransfer = pingUtil.balanceTransfer(10000,"balance",userId,"449",orderNo);
        return balanceTransfer;
    }

    @Syslog(title = "1",methods = "2",description = "3")
    @ResponseBody
    @RequestMapping(value = "/nowTime",method = RequestMethod.POST)
    public Object nowTime(HttpServletRequest request) throws SystemException {
        Map<String,Object>resultMap = new HashMap<>();
        try{
            String url = "http://localhost:8080/golf/rechargeSuccess";
            Map<String,Object>map1 = new HashMap<>();
            map1.put("rechargeId","220181101498395002880006");
            String map = HttpUtil.httpPost(url, map1);

            resultMap = JSONObject.parseObject(map);
            System.out.println(resultMap.get("code"));

        }catch (Exception e){
            throw new SystemException(Constant.ERR_GIVEMONEY);
        }

        return resultMap;
    }
}
