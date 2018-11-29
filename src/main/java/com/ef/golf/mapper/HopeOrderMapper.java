package com.ef.golf.mapper;

import com.ef.golf.pojo.HopeOrder;

public interface HopeOrderMapper {
    int deleteByPrimaryKey(Long hopeOrderId);

    int insert(HopeOrder record);

    int insertSelective(HopeOrder record);

    HopeOrder selectByPrimaryKey(Long hopeOrderId);

    int updateByPrimaryKeySelective(HopeOrder record);

    int updateByPrimaryKey(HopeOrder record);
    Integer getHopeIdByOrderId(Integer orderId);
}