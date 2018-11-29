package com.ef.golf.mapper;

import com.ef.golf.pojo.Order_log;

public interface Order_logMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(Order_log record);

    int insertSelective(Order_log record);

    Order_log selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(Order_log record);

    int updateByPrimaryKeyWithBLOBs(Order_log record);

    int updateByPrimaryKey(Order_log record);
    int lastOne();
}