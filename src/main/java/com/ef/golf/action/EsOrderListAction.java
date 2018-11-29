package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.EsOrder;
import com.ef.golf.pojo.GoodsMarketVo;
import com.ef.golf.pojo.TuiHuoAddress;
import com.ef.golf.service.EsOrderService;
import com.ef.golf.service.TuiHuoAddressService;
import com.ef.golf.vo.EsOrderVo;
import com.ef.golf.vo.OrderItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EsOrderListAction extends AbstractService {
    private Integer userId;
    private String orderState;
    private Integer pageNo;
    private Integer pageSize;


    @Autowired
    EsOrderService esOrderService;
    @Resource
    private TuiHuoAddressService tuiHuoAddressService;

    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();

        map1.put("pageNo", pageNo);
        map1.put("pageSize", pageSize);


        map2.put("pageNo", (pageNo - 1) * pageSize);
        map2.put("pageSize", pageSize);
        map2.put("userId", userId);
        map2.put("orderState", Integer.parseInt(orderState));//不知道mapper里为什么对字符串不屏蔽，暂时转为数字吧

        map.put("pageNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<EsOrderVo> orderList = esOrderService.userOrderList(map2);
        List resultLists = new ArrayList();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();


        //查询退货地址  跟订单绑定  目前只有一条，再外边查询减少查询次数
        TuiHuoAddress tuiHuoAddress= tuiHuoAddressService.selectTuiHuoAddress();

        for (EsOrderVo order : orderList) {
            map.put("order_id", order.getShopOrderId());
            List<OrderItemVo> itemList = esOrderService.orderList(map);
            order.setOrderItemVoList(itemList);
            order.setTuiHuoAddress(tuiHuoAddress);
            //map3.put("orderNo",order.getSn());
           /* String orderNo= order.getSn();
            maps.put(orderNo,itemList);*/
            resultLists.add(order);
        }
        int totalCount= esOrderService.orderCount(map2);
       map1.put("totalPage",totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1);
        map1.put("totalCount",totalCount);
        map1.put("resultList", resultLists);
        return map1;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
