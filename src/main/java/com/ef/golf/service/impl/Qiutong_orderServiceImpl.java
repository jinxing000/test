package com.ef.golf.service.impl;

import com.ef.golf.mapper.Qiutong_orderMapper;
import com.ef.golf.pojo.Qiutong_order;
import com.ef.golf.service.Qiutong_orderService;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.CourseWorkOrderVo;
import com.ef.golf.vo.QiuTongOrderListVo;
import com.ef.golf.vo.QiuTongWorkOrderVo;
import com.ef.golf.vo.CaddieWorkOrderVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * create by xzw
 * 2018年2月24日14:46:52
 *球童模块--预约下单
 */
@Repository
public class Qiutong_orderServiceImpl implements Qiutong_orderService {

    @Resource
    private Qiutong_orderMapper qiutongOrderMapper;



    @Override
    public int insertSelective(Qiutong_order record) {
        return qiutongOrderMapper.insertSelective(record);
    }

    //球童工单
    @Override
    public PageBean getQiuTongWorkOrderListPage(Integer userId, String orderState, Integer pageNo, Integer pageSize) {
        PageBean pageBean = new PageBean();
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("orderState",orderState);
        Integer count = qiutongOrderMapper.CaddieWorkOrderCount(map);
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<CaddieWorkOrderVo> list = qiutongOrderMapper.getQiuTongWorkOrderList(map);
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(count);
        pageBean.setResultList(list);
        return pageBean;
    }

    @Override
    public int updateWorkOrderStauts(QiuTongWorkOrderVo qiuTongWorkOrderVo) {
        /*return qiutongOrderMapper.updateWorkOrderStauts(qiuTongWorkOrderVo);*/
        return 1;
    }

    @Override
    public int delWorkOrder(QiuTongWorkOrderVo qiuTongWorkOrderVo) {
        /*return qiutongOrderMapper.delWorkOrder(qiuTongWorkOrderVo);*/
        return 1;
    }

    @Override
    public Integer getDateStatus(Integer orderId) {
        return qiutongOrderMapper.getDateStatus(orderId);
    }

    @Override
    public PageBean getQiuTongOrderList(String createUser, String orderState, Integer pageNo, Integer pageSize) {
        PageBean pageBean = new PageBean();
        Map<String,Object>map = new HashMap<>();
        map.put("createUser",createUser);
        map.put("orderState",orderState);
        Integer count = qiutongOrderMapper.getQiuTongOrderListCount(map);
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<QiuTongOrderListVo>list = qiutongOrderMapper.getQiuTongOrderList(map);
        pageBean.setResultList(list);
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(count);
        return pageBean;
    }

    @Override
    public Qiutong_order getQiuTongOrderDetails(Integer orderId) {
        return qiutongOrderMapper.getQiuTongOrderDetails(orderId);
    }

    @Override
    public QiuTongOrderListVo getQiuTongOrderDetailsByOrderId(Integer orderId) {
        return qiutongOrderMapper.getQiuTongOrderDetailsByOrderId(orderId);
    }
}
