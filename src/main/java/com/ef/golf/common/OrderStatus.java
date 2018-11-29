package com.ef.golf.common;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 统一存放订单（教练、球童、订场）的状态码和值
 * 1场地 2.教练 3.球童 4.球队 5.赛事 6.商家 7.商城 8.旅游 9.课程 10愿望)
 */
@Repository
public class OrderStatus {

    private Map<String,Object> orderStatus;

    public Map<String, Object> getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Map<String, Object> orderStatus) {
        this.orderStatus = orderStatus;
    }
}
