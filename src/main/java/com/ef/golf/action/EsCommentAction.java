package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.EsMemberComment;
import com.ef.golf.pojo.EsMemberOrderItem;
import com.ef.golf.pojo.OrderItem;
import com.ef.golf.service.EsMemberCommentSevice;
import com.ef.golf.service.EsMemberOrderItmeService;
import com.ef.golf.service.EsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EsCommentAction extends AbstractService {
    @Autowired
    EsMemberCommentSevice esMemberCommentSevice;
    @Autowired
    EsMemberOrderItmeService esMemberOrderItmeService;
    @Autowired
    EsOrderService esOrderService;

    private Integer goods_id;
    private Integer userId;
    private Integer grade;
    private String content;
    private Integer product_id;
    private Integer order_id;

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        EsMemberComment esMemberComment = new EsMemberComment();
        esMemberComment.setContent(content);
        esMemberComment.setDateline(System.currentTimeMillis() / 1000);
        esMemberComment.setGoodsId(goods_id);
        esMemberComment.setProductId(product_id);
        esMemberComment.setGrade(grade);
        esMemberComment.setType(1);
        esMemberComment.setStatus(1);
        int i = esMemberCommentSevice.insertSelective(esMemberComment);
        List<OrderItem> list = esOrderService.getItemList(order_id);
        if (list.size() > 0) {
            for (OrderItem item : list) {
                EsMemberOrderItem esMemberOrderItem = new EsMemberOrderItem();
                esMemberOrderItem.setGoodsId(goods_id);
                esMemberOrderItem.setProductId(product_id);
                esMemberOrderItem.setCommentTime(System.currentTimeMillis() / 1000);
                esMemberOrderItem.setItemId(item.getItemId());
                esMemberOrderItem.setMemberId(userId);
                esMemberOrderItem.setOrderId(order_id);
                esMemberOrderItem.setCommented("0");
                int s = esMemberOrderItmeService.insertSelective(esMemberOrderItem);
            }
        }

        if (i > 0) {
            map.put("mes", "保存成功");
        } else {
            map.put("mes", "保存失败");
        }

        return map;
    }
}
