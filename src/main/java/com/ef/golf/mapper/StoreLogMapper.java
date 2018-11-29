package com.ef.golf.mapper;

import com.ef.golf.pojo.StoreLog;

public interface StoreLogMapper {
    int deleteByPrimaryKey(Integer logid);

    int insert(StoreLog record);

    int insertSelective(StoreLog record);

    StoreLog selectByPrimaryKey(Integer logid);

    int updateByPrimaryKeySelective(StoreLog record);

    int updateByPrimaryKeyWithBLOBs(StoreLog record);

    int updateByPrimaryKey(StoreLog record);
}