package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.*;
import com.ef.golf.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 待支付 待发货 已发货 待评价 已完成 已取消
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GeneralAction extends AbstractService {

    private Integer userId;
    private Integer order_id;
    private Integer goods_id;

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    @Autowired
    EsMemberAddressService esMemberAddressService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    EsOrderService esOrderService;
    @Autowired
    TicketService ticketService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    UserService userService;

    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> maps = new HashMap<String, Object>();
        EsMemberAddress esMemberAddress = esMemberAddressService.specilAddress(userId);
        List<OrderItem> orderItemList = orderItemService.getItemList(order_id);
        EsOrder esOrder = esOrderService.selectByPrimaryKey(order_id);
        Tickets tickets = ticketService.selectByPrimaryKey(esOrder.getBonusId());
        Goods goods = goodsService.selectByPrimaryKey(goods_id);
        User user = userService.selectByPrimaryKey(userId.longValue());
     /*   String  userAddress = esMemberAddress.getProvince()+"-"+
                esMemberAddress.getCity();*/
        StringBuffer stringBuffer = new StringBuffer(esMemberAddress.getProvince());
        stringBuffer.append(esMemberAddress.getCity()).append(esMemberAddress.getRegion()).append(esMemberAddress.getAddr());
        map.put("userAddress", stringBuffer.toString());
        map.put("user", user);
        map.put("createTime", esOrder.getCreateTime());
        map.put("orderNo", esOrder.getSn());
        map.put("shippAmount", esOrder.getShippingAmount());
        map.put("tickets", tickets);
        map.put("payMoney", esOrder.getNeedPayMoney());
        map.put("speGoods", esOrderService.selectSpeItemVo(goods_id));
        return map;
    }


}
