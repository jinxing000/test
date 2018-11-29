package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.*;
import com.pingplusplus.model.Order;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付成功状态
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PaySuccessAction extends AbstractService {

    private String orderId;//此处orderId为ping++ id
    @Resource
    private OrderService orderService;

    public Object doService() {
        System.out.println("调了paysuccess=========================================================");
        Map<String, Object> map = new HashMap<>();
       /* String pingOrderId = orderService.getPingOrderId(orderId);*/
        //查根据ID查询订单
        try {
            Order order = Order.retrieve(orderId);
            if ("paid".equals(order.getStatus()) && order.getPaid() == true){
                map.put("status", order.getStatus());
                map.put("paid", order.getPaid());
                map.put("message", "支付成功");
            } else {
                map.put("paid", order.getPaid());
                map.put("message", "支付失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return map;

    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}


