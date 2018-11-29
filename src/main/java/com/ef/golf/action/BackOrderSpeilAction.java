package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.EsOrder;
import com.ef.golf.pojo.SellbackList;
import com.ef.golf.service.BackOrderService;
import com.ef.golf.service.EsOrderService;
import com.ef.golf.vo.BackGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 退货单详情
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BackOrderSpeilAction extends AbstractService {
    @Autowired
    BackOrderService backOrderService;
    @Autowired
    EsOrderService esOrderService;

    private Integer order_id;
    private Integer userId;

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> maps = new HashMap<String, Object>();
        EsOrder esOrder = esOrderService.selectByPrimaryKey(order_id);
        SellbackList sellbackList = backOrderService.ByPrimaryKey(order_id);
        List<BackGoodsVo> backGoodsVoList = backOrderService.BackGoodsVoList(sellbackList.getId());

        map.put("status", sellbackList.getTradestatus());
        map.put("orderNo", esOrder.getSn());
        map.put("backGoodsVoList", backGoodsVoList);
        return map;
    }
}
