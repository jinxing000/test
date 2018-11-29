package com.ef.golf.service;

import com.ef.golf.pojo.Site_order;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.SiteOrderDatailVo;
import com.ef.golf.vo.SiteOrderListVo;


public interface SiteOrderService {

    //查询球场订单详情
    SiteOrderDatailVo selectSiteOrderDetail(Integer order_id);
    //个人中心球场订单列表
    PageBean getSiteOrderList(String createUser,String orderState,Integer pageNo,Integer pageSize);
    //推送消息用
    Site_order getSiteOrderDetailsByOrderId(Integer orderId);

    SiteOrderListVo getSiteOrderDetails(Integer orderId);//适应手机端跳转
}
