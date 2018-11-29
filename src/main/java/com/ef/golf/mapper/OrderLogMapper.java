package com.ef.golf.mapper;

import com.ef.golf.pojo.OrderLog;

public interface OrderLogMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(OrderLog record);

    int insertSelective(OrderLog record);

    OrderLog selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(OrderLog record);

    int updateByPrimaryKeyWithBLOBs(OrderLog record);

    int updateByPrimaryKey(OrderLog record);
}