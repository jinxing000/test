package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.UpdateOrderService;
import com.ef.golf.util.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xzw on 2017/9/23.
 * 商城支付后需要更新订单
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UpdateOrderAction extends AbstractService {

    @Autowired
    UpdateOrderService updateOrderService;
    private Integer payment_id;
    private Integer order_id;

    public void setPayment_id(Integer payment_id) {
        this.payment_id = payment_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("payment_id", payment_id);
        map1.put("order_id", order_id);
        map1.put("status", OrderStatus.ORDER_PAY);
        map1.put("pay_status", OrderStatus.PAY_YES);
        int i = updateOrderService.upOrderPayStatus(map1);
        if (i > 0) {
            map.put("mes", "更新成功");
        } else {
            map.put("mes", "更新失败");
        }
        return map;
    }
}
