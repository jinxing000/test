package com.ef.golf.mapper;

import com.ef.golf.pojo.Qiutong_order;
import com.ef.golf.vo.QiuTongOrderListVo;
import com.ef.golf.vo.QiuTongWorkOrderVo;
import com.ef.golf.vo.CaddieWorkOrderVo;

import java.util.List;
import java.util.Map;

public interface Qiutong_orderMapper {
    int deleteByPrimaryKey(Integer qiutongOrderId);

    int insert(Qiutong_order record);

    int insertSelective(Qiutong_order record);

    Qiutong_order selectByPrimaryKey(Integer qiutongOrderId);

    int updateByPrimaryKeySelective(Qiutong_order record);

    int updateByPrimaryKey(Qiutong_order record);

    Integer CaddieWorkOrderCount(Map map);

    List<CaddieWorkOrderVo>getQiuTongWorkOrderList(Map map);
    Integer getDateStatus(Integer orderId);
    /** 用户预约球童列表查询 */
    List<QiuTongOrderListVo>getQiuTongOrderList(Map map);
    Integer getQiuTongOrderListCount(Map map);

    Qiutong_order getQiuTongOrderDetails(Integer orderId);

    QiuTongOrderListVo getQiuTongOrderDetailsByOrderId(Integer orderId);//适应手机端跳转详情
}