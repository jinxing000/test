package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.EsMemberCommentSevice;
import com.ef.golf.service.EsMemberOrderItmeService;
import com.ef.golf.service.EsOrderService;
import com.ef.golf.vo.OrderItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EsCommentListAction extends AbstractService {
    @Autowired
    EsMemberCommentSevice esMemberCommentSevice;
    @Autowired
    EsMemberOrderItmeService esMemberOrderItmeService;
    @Autowired
    EsOrderService esOrderService;


    private Integer order_id;
    private Integer pageNo;
    private Integer pageSize;

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public Object doService() throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map.put("pageNo", pageNo);
        map.put("pageSize", pageSize);

        pageNo = (pageNo - 1) * pageSize;

        map1.put("order_id", order_id);
        map1.put("pageNo", pageNo);
        map1.put("pageSize", pageSize);

        List<OrderItemVo> list = esMemberOrderItmeService.getOrderItemList(map1);


        map.put("resultList", list);

        map.put("totalCount", esMemberOrderItmeService.totalCount(order_id));


        return map;
    }
}
