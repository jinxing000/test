package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.service.OrderService;
import com.ef.golf.util.RedisLoginVerifyUtil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * create by xzw
 * 2018年6月8日15:17:30
 * 商城  确认收货 把状态更改为31
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ConfirmGetGoodsAction extends AbstractService {

    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;

    @Resource
    private OrderService orderService;

    //@NotNull
    private String uid;
    //@NotNull
    private String sid;
    //@NotNull
    private  Integer efOrderId;//主订单order_id
    //@NotNull
    private  Integer shopOrderId;//商城订单的order_id




    @Override
    public Object doService() throws Exception {
        Map<String,Object> map=new HashMap<>();
        Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);
        try {
            //确认收货  以为主订单和商城的订单状态是关联的，所以修改时要保证事务，
            // 所以需要在service 层完成两个操作
          int i=  orderService.confirmGetOrder(efOrderId,shopOrderId);


            map.put("mes", "确认收货成功");

        } catch (Exception e) {
            map.put("mes", "确认收货失败");
            throw new SystemException(Constant.ERR_UPDATE);
        }
        return map;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Integer getEfOrderId() {
        return efOrderId;
    }

    public void setEfOrderId(Integer efOrderId) {
        this.efOrderId = efOrderId;
    }

    public Integer getShopOrderId() {
        return shopOrderId;
    }

    public void setShopOrderId(Integer shopOrderId) {
        this.shopOrderId = shopOrderId;
    }
}
