package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.EsOrderService;
import com.ef.golf.util.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 更新订单支付状态
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ConfirmAndCancelAction extends AbstractService {
    private String isConfirm;//a 确认 c 取消
    private Integer order_id;

    @Autowired
    EsOrderService esOrderService;

    public void setIsConfirm(String isConfirm) {
        this.isConfirm = isConfirm;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> maps = new HashMap<String, Object>();
        map.put("order_id", order_id);
        int i = 0;
        if (isConfirm.equals("a")) {
            map.put("pay_status", OrderStatus.PAY_YES);
            i = esOrderService.updateOrderStatus(map);

        } else if (isConfirm.equals("c")) {
            map.put("pay_status", OrderStatus.PAY_NO);
            i = esOrderService.updateOrderStatus(map);
        }

        if (i > 0) {
            maps.put("mes", "更新成功");
        } else {

            maps.put("mes", "更新失败");
        }


        return maps;
    }
}
