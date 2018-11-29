package com.ef.golf.service;

import com.ef.golf.mapper.HopeOrderMapper;
import com.ef.golf.pojo.HopeOrder;

/**
 * com.ef.golf.service
 * Administrator
 * 2018/4/24 14:33
 */
public interface HopeOrderService {

    int deleteByPrimaryKey(Long hopeOrderId);

    int insert(HopeOrder record);

    int insertSelective(HopeOrder record);

    HopeOrder selectByPrimaryKey(Long hopeOrderId);

    int updateByPrimaryKeySelective(HopeOrder record);

    int updateByPrimaryKey(HopeOrder record);

    Integer getHopeIdByOrderId(Integer orderId);
}
