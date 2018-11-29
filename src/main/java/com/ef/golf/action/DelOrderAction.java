package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.OrderItem;
import com.ef.golf.service.DelOrderService;
import com.ef.golf.util.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xzw on 2017/9/23.
 * 商城删除或取消订单
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DelOrderAction extends AbstractService {
    private Integer order_id;
    private String isCancel;//a 取消订单 d 删除订单

    @Autowired
    DelOrderService delOrderService;

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public void setIsCancel(String isCancel) {
        this.isCancel = isCancel;
    }

    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("order_id", order_id);
        int i = 0;
        int j = 0;
        if (isCancel.equals("a")) {
            map1.put("status", OrderStatus.ORDER_CANCELLATION);
            i = delOrderService.updateOrderStruts(map1);
        } else if (isCancel.equals("d")) {
            i = delOrderService.deleteOrder(order_id);
            if (i > 0) {
                List<OrderItem> list = delOrderService.getItemList(order_id);
                for (OrderItem item : list) {
                    j = delOrderService.deleteByPrimaryKey(item.getItemId());
                }

            }

        }

        if (i > 0) {
            map.put("mes", "删除成功");
        } else {
            map.put("mes", "删除失败");
        }

        return map;
    }
}
