package com.ef.golf.service;

import com.ef.golf.pojo.Qiutong_order;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.QiuTongOrderListVo;
import com.ef.golf.vo.QiuTongWorkOrderVo;
import com.ef.golf.vo.CaddieWorkOrderVo;

import java.util.List;

/**
 * create by xzw
 * 2018年2月24日14:35:25
 * 球童模块-预约球童
 */
public interface Qiutong_orderService {

    //预约球童，下订单
    int insertSelective(Qiutong_order record);

    PageBean getQiuTongWorkOrderListPage(Integer userId, String orderState, Integer pageNo, Integer pageSize);

    int updateWorkOrderStauts(QiuTongWorkOrderVo qiuTongWorkOrderVo);

    int delWorkOrder(QiuTongWorkOrderVo qiuTongWorkOrderVo);

    Integer getDateStatus(Integer orderId);

    PageBean getQiuTongOrderList(String createUser,String orderState,Integer pageNo,Integer pageSize);

    Qiutong_order getQiuTongOrderDetails(Integer orderId);


    QiuTongOrderListVo getQiuTongOrderDetailsByOrderId(Integer orderId);//适应手机端跳转
}
