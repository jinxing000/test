package com.ef.golf.service.impl;

import com.ef.golf.mapper.Coach_orderMapper;
import com.ef.golf.pojo.Coach_order;
import com.ef.golf.service.CoachOrderService;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.CoachOrderListVo;
import com.ef.golf.vo.CoachWorkOrderVo;
import com.ef.golf.vo.CaddieWorkOrderVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class CoachOrderServiceImpl implements CoachOrderService {


    @Resource
    private Coach_orderMapper coach_orderMapper;


    @Override
    public CoachOrderListVo getCoachOrderDetailsByOrderId(Integer orderId) {
        return coach_orderMapper.getCoachOrderDetailsByOrderId(orderId);
    }
    @Override
    public Coach_order getCoachOrderByOrderId(Integer orderId) {
        return coach_orderMapper.getCoachOrderByOrderId(orderId);
    }

    @Override
    public PageBean<CoachWorkOrderVo> getCoachWorkOrderListPage(Integer coachId,String orderState,Integer pageNo,Integer pageSize) {
        PageBean pageBean = new PageBean();
        Map<String,Object> map = new HashMap<>();
        map.put("userId",coachId);
        map.put("orderState",orderState);
        Integer count = coach_orderMapper.coachWorkOrderCount(map);
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<CoachWorkOrderVo> list = coach_orderMapper.getCoachWorkOrderList(map);

        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(count);
        pageBean.setResultList(list);
        return pageBean;
    }

    @Override
    public int updateWorkOrderStauts(CaddieWorkOrderVo coachWorkOrderVo) {
        return /*coach_orderMapper.updateWorkOrderStauts(coachWorkOrderVo)*/1;
    }
    @Override
    public int delWorkOrder(CaddieWorkOrderVo coachWorkOrderVo) {
        return /*coach_orderMapper.delWorkOrder(coachWorkOrderVo)*/1;
    }

    @Override
    public PageBean getCoachOrderList(String createUser, String orderState, Integer pageNo, Integer pageSize) {
        PageBean pageBean = new PageBean();
        Map<String,Object> map = new HashMap<>();
        map.put("createUser",createUser);
        map.put("orderState",orderState);
        Integer count = coach_orderMapper.getCoachOrderListCount(map);
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<CoachOrderListVo> list = coach_orderMapper.getCoachOrderList(map);
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(count);
        pageBean.setResultList(list);
        return pageBean;
    }

    @Override
    public Coach_order getCoachOrderDetails(Integer orderId) {
        return coach_orderMapper.getCoachOrderDetails(orderId);
    }

}
