package com.ef.golf.action;


import com.ef.golf.common.Constant;
import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Account;
import com.ef.golf.service.AccountService;
import com.ef.golf.service.OrderService;
import com.ef.golf.util.AmountUtils;
import com.pingplusplus.model.Order;
import com.pingplusplus.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PayAction extends AbstractService{

    @Resource
    private AccountService accountService;
    @Resource
    private OrderService orderService;
    @Resource
    private PingUtil pingUtil;

    //@NotNull(message = "支付方式不为null")
    private String channel;
    //@NotNull(message = "orderId不为null")
    private String orderId;

    @Override
    public Object doService() throws Exception {

        Map<String,Object>map = new HashMap<>();

        String pingOrderNo=orderService.getPingOrderId(orderId);
        /** 根据orderid获取账户表id */
        Integer userAccountId = orderService.getUserAccountId(orderId);
        /** 获取userId */
        Integer userId = accountService.getAccountUserId(userAccountId);
        /** 获取用户余额信息 */
        Account account =  accountService.getUserBalance(userId);
        System.out.print("======"+"pingOrderNo"+pingOrderNo+"======");
        if(StringUtils.isEmpty(pingOrderNo)){
            throw new SystemException(Constant.ERR_QUERY-2);
        }
        com.ef.golf.pojo.Order myOrder = orderService.selectByPrimaryKey(Integer.valueOf(orderId));

        Date date = new Date();
        if(date.getTime()-myOrder.getCreateTime().getTime()>3600000){
            map.put("message","订单已过时");
            map.put("status",1);
            return map;
        }

        /** 余额支付处理 */
        if("balance".equals(channel)){
            //查询余额
            User user = pingUtil.userBalacnce(userId.toString());
            Long balance = user.getAvailableBalance();
            Order order=Order.retrieve(pingOrderNo);
            System.out.print("order="+order);
            Integer money = order.getAmount();
            /** 教练预约和课程预约需要校验 */


            if("2".equals(myOrder.getOrderType())||"9".equals(myOrder.getOrderType())){
                if((account.getCzBalance()+account.getSrBalance())<(Double.valueOf(AmountUtils.changeF2Y(money+"")))){
                    map.put("message","当前余额仅可用于订场、商城购物!");
                    map.put("status",1);
                    return map;
                }
            }
            if(balance>=money){
                Order obj=null;
                if(StringUtils.isNotEmpty(order.getObject())){
                    System.out.print(channel+"   "+order.getId());
                    try{
                        obj=pingUtil.pay(channel,order.getActualAmount(),order.getId());
                        map.put("orderId", obj.getId());
                        map.put("status", 0);
                    }catch (Exception e){//订单过期
                        throw new SystemException(Constant.ERR_ORDER_EXPRIY);
                    }
                    return map;
                }
            }else{
                map.put("message","余额不足!");
                map.put("status","2");
                return map;
            }
        }
        Order order=Order.retrieve(pingOrderNo);
        Order obj=null;
        try{
            if(StringUtils.isNotEmpty(order.getObject())){
                obj=pingUtil.pay(channel,order.getActualAmount(),order.getId());
                System.out.println(obj.toString());
            }
        }catch (Exception e){
            throw new SystemException(Constant.ERR_ORDER_EXPRIY);
        }

        return obj.toString();

       /* ping++余额充足 --> 告诉前台 --> 校验密码 --> pay*/
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }



}
