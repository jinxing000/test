/*
package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.DemandException;
import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.QueryException;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.*;
import com.ef.golf.service.*;
import com.ef.golf.util.*;
import com.ef.golf.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * Created by wxc on 2018/4/28.
 * 商家下单
 *//*

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MerchantOrderAction extends AbstractService {

    private String name = "商家";//标题

    private String describe = "商家描述";//描述

    private String payMoney;//价格

    //联系方式
    private String contactsPhone;
    //备注
    private String remark;
    //优惠券id
    private String ticketId;
    //商家IDor球会ID
    private Integer merchantId;
    //商家名or球会名
    private String merchantName;
    //去当前用户ID
    private String uid;
    private String sid;

    @Resource
    private TicketService ticketService;
    @Resource
    private OrderService orderService;
    @Resource
    private UserService userService;

    @Resource
    private HttpServletRequest request;
    @Resource
    private PingUtil pingUtil;
    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;
    @Resource
    private OrderNoUtil orderNoUtil;
    @Resource
    private MerchantOrderService merchantOrderService;
    @Resource
    private UserTicketService userTicketService;


    public Object doService() throws ParseException, QueryException, DemandException, SystemException, LoginException {
        */
/** 创建人id *//*

        Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);
        */
/** 现场确认金额 *//*

        double totalPrice = Double.valueOf(payMoney);//应付总价格
        */
/** 优惠处理后应付金额 *//*

        Double realPrice = 0.0;//优惠后的总价格
        */
/** 优惠券类型 满减或折扣 *//*

        realPrice = totalPrice;
        if (StringUtils.isNotEmpty(ticketId)) {
            Tickets tickets = ticketService.selectByPrimaryKey(Integer.valueOf(ticketId));
            if (tickets == null)
                throw new QueryException(Constant.ERR_QUERY - 4);
            //判断优惠券的归属
            if (tickets.getAttribution() != 1 && tickets.getAttribution() != 7)
                throw new DemandException(Constant.ERR_DEMAND - 3);
            if (tickets.getType() == 1) {*/
/** 折扣 *//*

                realPrice = totalPrice * tickets.getDiscount() / 100;
            }
            if (tickets.getType() == 2) {*/
/** 满减 *//*

                realPrice = totalPrice - tickets.getSpecialMoney();
            }
        }
        */
/** 确地价格 *//*

        String a = realPrice + "";
        if (!a.equals(payMoney.substring(0, payMoney.lastIndexOf(".") + 2))) {
            throw new SystemException(Constant.ERR_PARAMETERLESS);
        }
        String phoneNumber = userService.getInfo(userId).getPhone();
        String orderNo = orderNoUtil.orderNoGenerate("06", phoneNumber);
        String ip = HttpGetIpUtil.getIpAddress(request);
        String orderType = "6";
        String subject = name;
        String body = describe;
        */
/** 最终实付金额 *//*

        int amount = realPrice.intValue();
        */
/** 优惠金额 *//*

        double couponAmount = totalPrice - amount;
        //ping++执行创建订单
        com.pingplusplus.model.Order obj = pingUtil.createOrder(userId.toString(), orderNo, orderType, phoneNumber, subject, body, amount, ip);
        String pingOrderNo = obj.getMerchantOrderNo();
        //获取ping++生成的订单编号
        String pingId = obj.getId();

        Order order = orderService.saveMerchantOrder(userId, pingOrderNo, pingId, Double.valueOf(amount), totalPrice, couponAmount, ticketId);

        */
/** 本地订单详细 *//*

        MerchantOrder merchantOrder = new MerchantOrder();
        merchantOrder.setOrderId(order.getOrderId().longValue());
        merchantOrder.setMerchantId(merchantId.longValue());
        merchantOrder.setMerchantName(merchantName);
        if (ticketId != null) {
            merchantOrder.setTicketId(Long.valueOf(ticketId));
        }
        merchantOrder.setPayMoney((double) amount);
        merchantOrder.setCreateUser(userId.longValue());
        merchantOrder.setModifyUser(userId.longValue());
        merchantOrder.setCreateTime(new Date());
        merchantOrder.setModifyTime(new Date());
        */
/** 记录 *//*

        merchantOrderService.insertSelective(merchantOrder);
        */
/** 干掉用户优惠券 *//*

        userTicketService.updateUserTicketById(Integer.valueOf(userId), Integer.valueOf(ticketId));
        */
/** 返回数据 *//*

        Map<String, Object> orderMap = new HashMap<>();
        orderMap.put("user", new UserVo(uid, sid));
        orderMap.put("order", obj);
        orderMap.put("orderId", order.getOrderId());
        return orderMap;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
}
*/
