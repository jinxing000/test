package com.ef.golf.action;

import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.DemandException;
import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.QueryException;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Hope;
import com.ef.golf.service.*;
import com.ef.golf.util.*;
import com.ef.golf.vo.MineVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wxc on 2018/4/24.
 * 愿望帮助
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HopeHelpAction extends AbstractService {


    private String helpMoney;//帮助价格

    private Integer hopeId;

    private String uid;

    private String sid;
    @Resource
    private HopeService hopeService;

    public Object doService() throws ParseException, QueryException, DemandException, SystemException, LoginException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
        /** 返回数据 */
        Map<String, Object> map = new HashMap<>();
        /** 查询愿望信息 */
        Hope hope = hopeService.selectByPrimaryKey(hopeId);
        /** 当前时间等于失效时间 */
        if (hope.getReserved3() != null) {
            if (simpleDateFormat.parse(simpleDateFormat.format(hope.getReserved3())).getTime()
                    == simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime()) {
                hope.setHopeState("3");
                hopeService.updateByPrimaryKeySelective(hope);
                map.put("status", 1);
                map.put("message", "愿望已失效");
                return map;
            }
        }
        /** 貌似需要更新领取状态 */
        if (hope.getReserved4() != null) {
            if (simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime()
                    == simpleDateFormat.parse(simpleDateFormat.format(hope.getReserved4())).getTime()) {
                Hope ho3 = new Hope();
                ho3.setHopeId(hopeId);
                ho3.setGetStauts("2");
                hopeService.updateByPrimaryKeySelective(ho3);
            }
        }
        if ("2".equals(hope.getHopeState())) {
            map.put("status", 1);
            map.put("message", "愿望已实现");
            return map;
        }
        if ("1".equals(hope.getHopeState())) {
            if (hope.getHopeRealMoney() >= hope.getHopeMoney() || hope.getHopeRealMoney() + Double.valueOf(helpMoney) >= hope.getHopeMoney()) {
                Hope ho1 = new Hope();
                ho1.setHopeId(hopeId);
                ho1.setRealizeTime(new Date());
                ho1.setGetStauts("0");
                ho1.setHopeState("2");
                /** 如果是商品愿望设置最终领取时间 */
                if (hope.getHopeType().equals("2")) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    /** 往后推七天 */
                    calendar.set(Calendar.DATE, 7);
                    Date dt = calendar.getTime();
                    ho1.setReserved4(dt);
                }
                hopeService.updateByPrimaryKeySelective(ho1);
                map.put("status", 1);
                map.put("message", "愿望已实现");
                return map;
            }
            /** 获取该愿望每份支持金额 */
/*            Double everyHopeMoney = hope.getEveryHopeMoney();
            if(everyHopeMoney.toString().equals(helpMoney.substring(0,helpMoney.lastIndexOf(".")+2))){
                String ip= HttpGetIpUtil.getIpAddress(request);
                String orderType="10";
                String subject="愿望";
                String body="愿望支持";
                String orderNo=orderNoUtil.orderNoGenerate("10",phoneNumber);
                //ping++执行创建订单
                com.pingplusplus.model.Order obj=pingUtil.createOrder(userId.toString(),orderNo,orderType,phoneNumber,subject,body,Double.valueOf(helpMoney).intValue(),ip);
                String pingOrderNo=obj.getMerchantOrderNo();
                //获取ping++生成的订单编号
                String orderId=obj.getId();
                *//** 更新已实现金额 *//*
                *//*Hope ho2 = new Hope();
                ho2.setHopeId(hopeId);
                ho2.setHopeRealMoney(hope.getHopeRealMoney()+Double.valueOf(helpMoney));
                hopeService.updateByPrimaryKeySelective(ho2);*//*
                *//** 本地生成一条订单记录 *//*
                Order order = orderService.saveHopeOrder(userId,pingOrderNo,orderId,Double.valueOf(helpMoney));
                *//** 生成愿望Order *//*
                HopeOrder hopeOrder = new HopeOrder();
                hopeOrder.setCreateTime(new Date());
                hopeOrder.setCreateUser(userId+"");
                hopeOrder.setHopeId(hopeId);
                hopeOrder.setModifyTime(new Date());
                hopeOrder.setModifyUser(userId+"");
                hopeOrder.setOrderId(order.getOrderId());
                hopeOrder.setUserId(userId);
                hopeOrder.setPayMoney(Double.valueOf(helpMoney));
                hopeOrderService.insertSelective(hopeOrder);
                *//** 返回客户端数据 *//*
                map.put("user",new UserVo(sid,uid));
                map.put("order",obj);
                map.put("orderId",order.getOrderId());
                return map;
            }*/
        }
        map.put("status", 0);
        map.put("message", "");
        return map;
    }

    public void setHopeId(Integer hopeId) {
        this.hopeId = hopeId;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setHelpMoney(String helpMoney) {
        this.helpMoney = helpMoney;
    }
}
