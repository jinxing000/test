package com.ef.golf.service.impl;

import com.ef.golf.mapper.Site_orderMapper;
import com.ef.golf.pojo.Site_order;
import com.ef.golf.service.SiteOrderService;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.SiteOrderDatailVo;
import com.ef.golf.vo.SiteOrderListVo;
import com.ef.golf.vo.SiteOrderVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class SiteOrderServiceImpl implements SiteOrderService {


    @Resource
    private Site_orderMapper site_orderMapper;

    public SiteOrderDatailVo selectSiteOrderDetail(Integer order_id) {

        SiteOrderDatailVo siteOrderDatailVo=site_orderMapper.selectSiteOrderDetail(order_id);

        return siteOrderDatailVo;
    }

    @Override
    public PageBean getSiteOrderList(String createUser, String orderState, Integer pageNo, Integer pageSize) {
        Map<String,Object>map = new HashMap<>();
        PageBean pageBean = new PageBean();
        map.put("createUser",createUser);
        map.put("orderState",orderState);
        Integer count = site_orderMapper.getSiteOrderListCount(map);
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<SiteOrderListVo>list = site_orderMapper.getSiteOrderList(map);
        pageBean.setTotalCount(count);
        pageBean.setPageSize(pageSize);
        pageBean.setPageNo(pageNo);
        pageBean.setResultList(list);
        return pageBean;
    }

    @Override
    public Site_order getSiteOrderDetailsByOrderId(Integer orderId) {
        return site_orderMapper.getSiteOrderDetailsByOrderId(orderId);
    }

    @Override
    public SiteOrderListVo getSiteOrderDetails(Integer orderId) {
        return site_orderMapper.getSiteOrderDetails(orderId);
    }
}
